USE team27_projectdb;
SET hive.resultset.use.unique.column.names=false;

DROP TABLE IF EXISTS q12_results;
CREATE EXTERNAL TABLE q12_results (
  distance_bin STRING,
  fare_bin STRING,
  trips BIGINT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q12';

INSERT OVERWRITE TABLE q12_results
SELECT
  CASE WHEN trip_miles < 2 THEN '0-2' WHEN trip_miles < 5 THEN '2-5' WHEN trip_miles < 10 THEN '5-10' WHEN trip_miles < 20 THEN '10-20' ELSE '20-50' END AS distance_bin,
  CASE WHEN fare < 10 THEN '0-10' WHEN fare < 20 THEN '10-20' WHEN fare < 40 THEN '20-40' WHEN fare < 80 THEN '40-80' ELSE '80+' END AS fare_bin,
  COUNT(*) AS trips
FROM taxi_trips_part_bucket
WHERE fare > 0 AND trip_miles > 0 AND trip_seconds > 0 AND trip_miles <= 50 AND fare <= 200
GROUP BY
  CASE WHEN trip_miles < 2 THEN '0-2' WHEN trip_miles < 5 THEN '2-5' WHEN trip_miles < 10 THEN '5-10' WHEN trip_miles < 20 THEN '10-20' ELSE '20-50' END,
  CASE WHEN fare < 10 THEN '0-10' WHEN fare < 20 THEN '10-20' WHEN fare < 40 THEN '20-40' WHEN fare < 80 THEN '40-80' ELSE '80+' END;

SELECT * FROM q12_results;

INSERT OVERWRITE DIRECTORY 'project/output/q12'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
SELECT distance_bin, fare_bin, trips FROM q12_results;
