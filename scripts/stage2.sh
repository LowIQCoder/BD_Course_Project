#!/usr/bin/env bash
set -euo pipefail

start=$SECONDS

cd "$(dirname "$0")/.."

password=$(head -n 1 secrets/.hive.pass)
url="jdbc:hive2://hadoop-03.uni.innopolis.ru:10001"
user="team27"

log_dir="output/logs/stage2"
mkdir -p output "$log_dir"

run_hive() {
  local file="$1"
  local out="${2:-}"
  local name
  name=$(basename "$file" .sql)
  if [ -z "$out" ]; then
    out="$log_dir/${name}.log"
  fi
  beeline -u "$url" -n "$user" -p "$password" -f "$file" > "$out" 2>&1
}
echo "Storage setup"
run_hive "sql/stage2_00_storage_setup.sql" "output/hive_results.txt"

echo "Executing q1"
run_hive "sql/stage2_01_monthly_metrics.sql"

echo "Executing q2"
run_hive "sql/stage2_02_weekday_hour_demand.sql"

echo "Executing q3"
run_hive "sql/stage2_03_distance_fare_distribution.sql"

echo "Executing q4"
run_hive "sql/stage2_04_pickup_community_metrics.sql"

echo "Executing q5"
run_hive "sql/stage2_05_dropoff_community_metrics.sql"

echo "Executing q6"
run_hive "sql/stage2_06_payment_type_metrics.sql"

echo "Executing q7"
run_hive "sql/stage2_07_fare_bins.sql"

echo "Executing q8"
run_hive "sql/stage2_08_correlation_matrix.sql"

echo "Executing q9"
run_hive "sql/stage2_09_pickup_heatmap.sql"

echo "Executing q10"
run_hive "sql/stage2_10_od_community_flows.sql"

echo "Executing q11"
run_hive "sql/stage2_11_hourly_fare_metrics.sql"

echo "Executing q12"
run_hive "sql/stage2_12_distance_fare_bins.sql"

echo "Executing q13"
run_hive "sql/stage2_13_speed_by_hour.sql"

echo "Executing q14"
run_hive "sql/stage2_14_fare_per_mile.sql"

echo "Executing q15"
run_hive "sql/stage2_15_missing_values.sql"

echo "Saving data to hdfs"
echo "month,trips,avg_fare,avg_trip_miles" > "output/q1.csv"
hdfs dfs -cat "project/output/q1"/* >> "output/q1.csv"

echo "weekday_name,hour,trips" > "output/q2.csv"
hdfs dfs -cat "project/output/q2"/* >> "output/q2.csv"

echo "distance_bin,trips,avg_fare,median_fare" > "output/q3.csv"
hdfs dfs -cat "project/output/q3"/* >> "output/q3.csv"

echo "pickup_community_area,trips,avg_fare,avg_trip_miles" > "output/q4.csv"
hdfs dfs -cat "project/output/q4"/* >> "output/q4.csv"

echo "dropoff_community_area,trips,avg_fare,avg_trip_miles" > "output/q5.csv"
hdfs dfs -cat "project/output/q5"/* >> "output/q5.csv"

echo "payment_type,trips,avg_fare,median_fare,avg_tip,avg_trip_total" > "output/q6.csv"
hdfs dfs -cat "project/output/q6"/* >> "output/q6.csv"

echo "fare_bin,trips" > "output/q7.csv"
hdfs dfs -cat "project/output/q7"/* >> "output/q7.csv"

echo "var_x,var_y,corr_value" > "output/q8.csv"
hdfs dfs -cat "project/output/q8"/* >> "output/q8.csv"

echo "pickup_lat,pickup_lon,trips,avg_fare" > "output/q9.csv"
hdfs dfs -cat "project/output/q9"/* >> "output/q9.csv"

echo "pickup_community_area,dropoff_community_area,trips,avg_fare,avg_trip_miles" > "output/q10.csv"
hdfs dfs -cat "project/output/q10"/* >> "output/q10.csv"

echo "hour,trips,avg_fare,median_fare" > "output/q11.csv"
hdfs dfs -cat "project/output/q11"/* >> "output/q11.csv"

echo "distance_bin,fare_bin,trips" > "output/q12.csv"
hdfs dfs -cat "project/output/q12"/* >> "output/q12.csv"

echo "hour,trips,avg_speed_mph,median_speed_mph" > "output/q13.csv"
hdfs dfs -cat "project/output/q13"/* >> "output/q13.csv"

echo "distance_bin,trips,avg_fare_per_mile,median_fare_per_mile" > "output/q14.csv"
hdfs dfs -cat "project/output/q14"/* >> "output/q14.csv"

echo "column_name,missing_count,missing_pct" > "output/q15.csv"
hdfs dfs -cat "project/output/q15"/* >> "output/q15.csv"

duration=$(( SECONDS - start ))
echo "Stage II finished successfully. Execution time: $duration seconds"

echo "Stage II execution time: $duration seconds" >> output/execution_times.txt

