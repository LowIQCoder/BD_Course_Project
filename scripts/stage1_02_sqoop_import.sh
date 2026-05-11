#!/usr/bin/env bash
set -euo pipefail

PGHOST="${PGHOST:-hadoop-04.uni.innopolis.ru}"
PGPORT="${PGPORT:-5432}"
PGDATABASE="${PGDATABASE:-team27_projectdb}"
PGUSER="${PGUSER:-team27}"
PGPASSWORD="${PGPASSWORD:-$(head -n 1 secrets/.psql.pass)}"

BASE_HDFS_DIR="${BASE_HDFS_DIR:-/user/team27/project/warehouse}"
JDBC_URL="jdbc:postgresql://${PGHOST}:${PGPORT}/${PGDATABASE}?currentSchema=chicago_taxi"

mkdir -p output
hdfs dfs -mkdir -p "${BASE_HDFS_DIR}"

hdfs dfs -rm -r -f "${BASE_HDFS_DIR}/fact_trips" || true
hdfs dfs -rm -r -f "${BASE_HDFS_DIR}/dim_taxi" || true
hdfs dfs -rm -r -f "${BASE_HDFS_DIR}/dim_company" || true
hdfs dfs -rm -r -f "${BASE_HDFS_DIR}/dim_payment_type" || true

sqoop import \
  --connect "${JDBC_URL}" \
  --username "${PGUSER}" \
  --password "${PGPASSWORD}" \
  --table "fact_trips" \
  --target-dir "${BASE_HDFS_DIR}/fact_trips" \
  --delete-target-dir \
  --as-avrodatafile \
  --compress \
  --compression-codec snappy \
  --num-mappers 4 \
  --split-by taxi_id \
  --bindir output \
  --outdir output

sqoop import \
  --connect "${JDBC_URL}" \
  --username "${PGUSER}" \
  --password "${PGPASSWORD}" \
  --table "dim_taxi" \
  --target-dir "${BASE_HDFS_DIR}/dim_taxi" \
  --delete-target-dir \
  --as-avrodatafile \
  --compress \
  --compression-codec snappy \
  --num-mappers 1 \
  --bindir output \
  --outdir output

sqoop import \
  --connect "${JDBC_URL}" \
  --username "${PGUSER}" \
  --password "${PGPASSWORD}" \
  --table "dim_company" \
  --target-dir "${BASE_HDFS_DIR}/dim_company" \
  --delete-target-dir \
  --as-avrodatafile \
  --compress \
  --compression-codec snappy \
  --num-mappers 1 \
  --bindir output \
  --outdir output

sqoop import \
  --connect "${JDBC_URL}" \
  --username "${PGUSER}" \
  --password "${PGPASSWORD}" \
  --table "dim_payment_type" \
  --target-dir "${BASE_HDFS_DIR}/dim_payment_type" \
  --delete-target-dir \
  --as-avrodatafile \
  --compress \
  --compression-codec snappy \
  --num-mappers 1 \
  --bindir output \
  --outdir output

echo "Sqoop import finished successfully."
