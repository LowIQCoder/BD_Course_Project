USE team27_projectdb;
SET hive.resultset.use.unique.column.names=false;

DROP TABLE IF EXISTS q15_results;
CREATE EXTERNAL TABLE q15_results (
  column_name STRING,
  missing_count BIGINT,
  missing_pct DOUBLE
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q15';

INSERT OVERWRITE TABLE q15_results
SELECT column_name, missing_count, missing_pct
FROM (
  SELECT
    column_name,
    CAST(missing_count AS BIGINT) AS missing_count,
    ROUND(missing_pct, 4) AS missing_pct
  FROM (
    SELECT
      COUNT(*) AS total_rows,
      SUM(CASE WHEN trip_id IS NULL THEN 1 ELSE 0 END) AS trip_id_missing,
    SUM(CASE WHEN taxi_id IS NULL THEN 1 ELSE 0 END) AS taxi_id_missing,
    SUM(CASE WHEN company_code IS NULL THEN 1 ELSE 0 END) AS company_code_missing,
    SUM(CASE WHEN payment_type IS NULL THEN 1 ELSE 0 END) AS payment_type_missing,
    SUM(CASE WHEN trip_start_timestamp IS NULL THEN 1 ELSE 0 END) AS trip_start_timestamp_missing,
    SUM(CASE WHEN trip_end_timestamp IS NULL THEN 1 ELSE 0 END) AS trip_end_timestamp_missing,
    SUM(CASE WHEN trip_seconds IS NULL THEN 1 ELSE 0 END) AS trip_seconds_missing,
    SUM(CASE WHEN trip_miles IS NULL THEN 1 ELSE 0 END) AS trip_miles_missing,
    SUM(CASE WHEN pickup_census_tract IS NULL THEN 1 ELSE 0 END) AS pickup_census_tract_missing,
    SUM(CASE WHEN dropoff_census_tract IS NULL THEN 1 ELSE 0 END) AS dropoff_census_tract_missing,
    SUM(CASE WHEN pickup_community_area IS NULL THEN 1 ELSE 0 END) AS pickup_community_area_missing,
    SUM(CASE WHEN dropoff_community_area IS NULL THEN 1 ELSE 0 END) AS dropoff_community_area_missing,
    SUM(CASE WHEN fare IS NULL THEN 1 ELSE 0 END) AS fare_missing,
    SUM(CASE WHEN tips IS NULL THEN 1 ELSE 0 END) AS tips_missing,
    SUM(CASE WHEN tolls IS NULL THEN 1 ELSE 0 END) AS tolls_missing,
    SUM(CASE WHEN extras IS NULL THEN 1 ELSE 0 END) AS extras_missing,
    SUM(CASE WHEN trip_total IS NULL THEN 1 ELSE 0 END) AS trip_total_missing,
    SUM(CASE WHEN pickup_latitude IS NULL THEN 1 ELSE 0 END) AS pickup_latitude_missing,
    SUM(CASE WHEN pickup_longitude IS NULL THEN 1 ELSE 0 END) AS pickup_longitude_missing,
    SUM(CASE WHEN dropoff_latitude IS NULL THEN 1 ELSE 0 END) AS dropoff_latitude_missing,
    SUM(CASE WHEN dropoff_longitude IS NULL THEN 1 ELSE 0 END) AS dropoff_longitude_missing,
    SUM(CASE WHEN trip_year IS NULL THEN 1 ELSE 0 END) AS trip_year_missing,
    SUM(CASE WHEN trip_month IS NULL THEN 1 ELSE 0 END) AS trip_month_missing,
    SUM(CASE WHEN trip_day IS NULL THEN 1 ELSE 0 END) AS trip_day_missing,
    SUM(CASE WHEN trip_hour IS NULL THEN 1 ELSE 0 END) AS trip_hour_missing,
    SUM(CASE WHEN trip_weekday IS NULL THEN 1 ELSE 0 END) AS trip_weekday_missing,
    SUM(CASE WHEN is_valid_end_timestamp IS NULL THEN 1 ELSE 0 END) AS is_valid_end_timestamp_missing,
    SUM(CASE WHEN is_valid_duration IS NULL THEN 1 ELSE 0 END) AS is_valid_duration_missing,
    SUM(CASE WHEN is_valid_amounts IS NULL THEN 1 ELSE 0 END) AS is_valid_amounts_missing
    FROM taxi_trips_part_bucket
  ) c
  LATERAL VIEW stack(29, 'trip_id', trip_id_missing, 100.0 * trip_id_missing / total_rows, 'taxi_id', taxi_id_missing, 100.0 * taxi_id_missing / total_rows, 'company_code', company_code_missing, 100.0 * company_code_missing / total_rows, 'payment_type', payment_type_missing, 100.0 * payment_type_missing / total_rows, 'trip_start_timestamp', trip_start_timestamp_missing, 100.0 * trip_start_timestamp_missing / total_rows, 'trip_end_timestamp', trip_end_timestamp_missing, 100.0 * trip_end_timestamp_missing / total_rows, 'trip_seconds', trip_seconds_missing, 100.0 * trip_seconds_missing / total_rows, 'trip_miles', trip_miles_missing, 100.0 * trip_miles_missing / total_rows, 'pickup_census_tract', pickup_census_tract_missing, 100.0 * pickup_census_tract_missing / total_rows, 'dropoff_census_tract', dropoff_census_tract_missing, 100.0 * dropoff_census_tract_missing / total_rows, 'pickup_community_area', pickup_community_area_missing, 100.0 * pickup_community_area_missing / total_rows, 'dropoff_community_area', dropoff_community_area_missing, 100.0 * dropoff_community_area_missing / total_rows, 'fare', fare_missing, 100.0 * fare_missing / total_rows, 'tips', tips_missing, 100.0 * tips_missing / total_rows, 'tolls', tolls_missing, 100.0 * tolls_missing / total_rows, 'extras', extras_missing, 100.0 * extras_missing / total_rows, 'trip_total', trip_total_missing, 100.0 * trip_total_missing / total_rows, 'pickup_latitude', pickup_latitude_missing, 100.0 * pickup_latitude_missing / total_rows, 'pickup_longitude', pickup_longitude_missing, 100.0 * pickup_longitude_missing / total_rows, 'dropoff_latitude', dropoff_latitude_missing, 100.0 * dropoff_latitude_missing / total_rows, 'dropoff_longitude', dropoff_longitude_missing, 100.0 * dropoff_longitude_missing / total_rows, 'trip_year', trip_year_missing, 100.0 * trip_year_missing / total_rows, 'trip_month', trip_month_missing, 100.0 * trip_month_missing / total_rows, 'trip_day', trip_day_missing, 100.0 * trip_day_missing / total_rows, 'trip_hour', trip_hour_missing, 100.0 * trip_hour_missing / total_rows, 'trip_weekday', trip_weekday_missing, 100.0 * trip_weekday_missing / total_rows, 'is_valid_end_timestamp', is_valid_end_timestamp_missing, 100.0 * is_valid_end_timestamp_missing / total_rows, 'is_valid_duration', is_valid_duration_missing, 100.0 * is_valid_duration_missing / total_rows, 'is_valid_amounts', is_valid_amounts_missing, 100.0 * is_valid_amounts_missing / total_rows) s AS column_name, missing_count, missing_pct
) ranked
ORDER BY missing_pct DESC
LIMIT 20;

SELECT * FROM q15_results;

INSERT OVERWRITE DIRECTORY 'project/output/q15'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
SELECT column_name, missing_count, missing_pct FROM q15_results;
