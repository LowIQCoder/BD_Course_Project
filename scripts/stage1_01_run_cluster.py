"""
Creates and populates database
"""
import glob
import os
import zipfile
import psycopg2

PGHOST = "hadoop-04.uni.innopolis.ru"
PGPORT = 5432
PGDATABASE = "team27_projectdb"
PGUSER = "team27"

with open("secrets/.psql.pass", mode='r', encoding='utf-8') as f:
    PGPASSWORD = f.read().strip()

os.makedirs("data/archive", exist_ok=True)

stage_files = {
    "chicago_taxi_trips_2016_01.csv",
    "chicago_taxi_trips_2016_04.csv",
    "chicago_taxi_trips_2016_07.csv",
    "chicago_taxi_trips_2016_10.csv",
}

print("Extracting Kaggle archive if needed...")

zip_files = glob.glob("data/archive/*.zip")
for zip_path in zip_files:
    print(f"Extracting {zip_path} ...")
    with zipfile.ZipFile(zip_path, "r") as zip_ref:
        zip_ref.extractall("data/archive")

print("Extraction completed.")

files = []
for file_name in stage_files:
    if file_name not in stage_files:
        continue

    path = os.path.join("data/archive", file_name)
    if os.path.exists(path):
        files.append(path)
    else:
        print(f"Warning: {file_name} not found after extraction")

if not files:
    raise SystemExit("No required CSV files found in data/archive")

conn = psycopg2.connect(
    host=PGHOST,
    port=PGPORT,
    dbname=PGDATABASE,
    user=PGUSER,
    password=PGPASSWORD,
)

conn.autocommit = False
cur = conn.cursor()

print("Running schema creation...")
with open("sql/stage1_01_schema_cluster.sql", mode='r', encoding='utf-8') as f:
    cur.execute(f.read())
conn.commit()

print("Loading CSV files into staging table...")

COPY_SQL = """
COPY chicago_taxi.staging_raw_trips
FROM STDIN
WITH (FORMAT csv, HEADER true)
"""

for path in files:
    print(f"Loading {path} ...")
    with open(path, "r", encoding="utf-8") as infile:
        cur.copy_expert(COPY_SQL, infile)

conn.commit()

print("Transforming staging data into dimensions and fact table...")
with open("sql/stage1_02_transform.sql", mode='r', encoding='utf-8') as f:
    cur.execute(f.read())
conn.commit()

print("Running verification queries...")
with open("sql/stage1_03_verify.sql", mode='r', encoding='utf-8') as f:
    cur.execute(f.read())

    try:
        while True:
            if cur.description:
                print(cur.fetchall())
            if not cur.nextset():
                break
    except psycopg2.Error:
        pass

cur.close()
conn.close()

print("Cluster schema creation and population finished successfully.")
