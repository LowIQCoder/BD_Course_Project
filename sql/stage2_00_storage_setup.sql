CREATE DATABASE IF NOT EXISTS team27_projectdb LOCATION 'project/hive/warehouse';
USE team27_projectdb;

SET hive.execution.engine=tez;
SET hive.exec.dynamic.partition=true;
SET hive.exec.dynamic.partition.mode=nonstrict;
SET hive.enforce.bucketing=true;

DROP TABLE IF EXISTS fact_trips_raw;
CREATE EXTERNAL TABLE fact_trips_raw
STORED AS AVRO
LOCATION 'project/warehouse/fact_trips'
TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/fact_trips.avsc');

DROP TABLE IF EXISTS dim_company_raw;
CREATE EXTERNAL TABLE dim_company_raw
STORED AS AVRO
LOCATION 'project/warehouse/dim_company'
TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/dim_company.avsc');

DROP TABLE IF EXISTS dim_payment_type_raw;
CREATE EXTERNAL TABLE dim_payment_type_raw
STORED AS AVRO
LOCATION 'project/warehouse/dim_payment_type'
TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/dim_payment_type.avsc');

DROP TABLE IF EXISTS dim_taxi_raw;
CREATE EXTERNAL TABLE dim_taxi_raw
STORED AS AVRO
LOCATION 'project/warehouse/dim_taxi'
TBLPROPERTIES ('avro.schema.url'='project/warehouse/avsc/dim_taxi.avsc');

SHOW TABLES;
DESCRIBE fact_trips_raw;
SELECT COUNT(*) AS raw_rows FROM fact_trips_raw;
SELECT * FROM fact_trips_raw LIMIT 10;

DROP TABLE IF EXISTS taxi_trips_part_bucket;
CREATE EXTERNAL TABLE taxi_trips_part_bucket (
  trip_id BIGINT,
  taxi_id INT,
  company_code INT,
  payment_type STRING,
  trip_start_timestamp TIMESTAMP,
  trip_end_timestamp TIMESTAMP,
  trip_seconds INT,
  trip_miles DOUBLE,
  pickup_census_tract INT,
  dropoff_census_tract INT,
  pickup_community_area INT,
  dropoff_community_area INT,
  fare DOUBLE,
  tips DOUBLE,
  tolls DOUBLE,
  extras DOUBLE,
  trip_total DOUBLE,
  pickup_latitude DOUBLE,
  pickup_longitude DOUBLE,
  dropoff_latitude DOUBLE,
  dropoff_longitude DOUBLE,
  trip_year INT,
  trip_day INT,
  trip_hour INT,
  trip_weekday INT,
  is_valid_end_timestamp BOOLEAN,
  is_valid_duration BOOLEAN,
  is_valid_amounts BOOLEAN
)
PARTITIONED BY (trip_month INT)
CLUSTERED BY (pickup_community_area) INTO 16 BUCKETS
STORED AS AVRO
TBLPROPERTIES ('AVRO.COMPRESS'='SNAPPY');

INSERT OVERWRITE TABLE taxi_trips_part_bucket PARTITION (trip_month)
SELECT
  trip_id,
  taxi_id,
  company_code,
  payment_type,
  CAST(from_unixtime(CAST(trip_start_timestamp / 1000 AS BIGINT)) AS TIMESTAMP) AS trip_start_timestamp,
  CAST(from_unixtime(CAST(trip_end_timestamp / 1000 AS BIGINT)) AS TIMESTAMP) AS trip_end_timestamp,
  trip_seconds,
  CAST(trip_miles AS DOUBLE) AS trip_miles,
  pickup_census_tract,
  dropoff_census_tract,
  pickup_community_area,
  dropoff_community_area,
  CAST(fare AS DOUBLE) AS fare,
  CAST(tips AS DOUBLE) AS tips,
  CAST(tolls AS DOUBLE) AS tolls,
  CAST(extras AS DOUBLE) AS extras,
  CAST(trip_total AS DOUBLE) AS trip_total,
  CAST(pickup_latitude_code AS DOUBLE) AS pickup_latitude,
  CAST(pickup_longitude_code AS DOUBLE) AS pickup_longitude,
  CAST(dropoff_latitude_code AS DOUBLE) AS dropoff_latitude,
  CAST(dropoff_longitude_code AS DOUBLE) AS dropoff_longitude,
  trip_year,
  trip_day,
  trip_hour,
  trip_weekday,
  is_valid_end_timestamp,
  is_valid_duration,
  is_valid_amounts,
  trip_month
FROM fact_trips_raw
WHERE trip_start_timestamp IS NOT NULL
  AND CAST(fare AS DOUBLE) > 0
  AND CAST(trip_miles AS DOUBLE) > 0
  AND trip_seconds > 0
  AND CAST(fare AS DOUBLE) <= 300
  AND CAST(trip_miles AS DOUBLE) <= 100
  AND trip_seconds / 60.0 <= 240
  AND is_valid_duration = true
  AND is_valid_amounts = true;

SHOW TABLES;
DESCRIBE FORMATTED taxi_trips_part_bucket;
SHOW PARTITIONS taxi_trips_part_bucket;
SELECT COUNT(*) AS clean_rows FROM taxi_trips_part_bucket;
SELECT * FROM taxi_trips_part_bucket LIMIT 10;

-- Keep fact_trips_raw for Stage III raw Spark access.
DROP TABLE IF EXISTS dim_company_raw;
DROP TABLE IF EXISTS dim_payment_type_raw;
DROP TABLE IF EXISTS dim_taxi_raw;
SHOW TABLES;
