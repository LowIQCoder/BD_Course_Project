USE team27_projectdb;
SET hive.resultset.use.unique.column.names=false;

DROP TABLE IF EXISTS q14_results;
CREATE EXTERNAL TABLE q14_results (
  distance_bin STRING,
  trips BIGINT,
  avg_fare_per_mile DOUBLE,
  median_fare_per_mile DOUBLE
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q14';

INSERT OVERWRITE TABLE q14_results
SELECT
  CASE WHEN trip_miles < 1 THEN '0-1' WHEN trip_miles < 2 THEN '1-2' WHEN trip_miles < 5 THEN '2-5' WHEN trip_miles < 10 THEN '5-10' WHEN trip_miles < 20 THEN '10-20' ELSE '20-50' END AS distance_bin,
  COUNT(*) AS trips,
  AVG(fare / trip_miles) AS avg_fare_per_mile,
  percentile_approx(fare / trip_miles, 0.5) AS median_fare_per_mile
FROM taxi_trips_part_bucket
WHERE fare > 0 AND trip_miles > 0 AND trip_seconds > 0 AND trip_miles <= 50 AND fare / trip_miles BETWEEN 0 AND 100
GROUP BY CASE WHEN trip_miles < 1 THEN '0-1' WHEN trip_miles < 2 THEN '1-2' WHEN trip_miles < 5 THEN '2-5' WHEN trip_miles < 10 THEN '5-10' WHEN trip_miles < 20 THEN '10-20' ELSE '20-50' END;

SELECT * FROM q14_results;

INSERT OVERWRITE DIRECTORY 'project/output/q14'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
SELECT distance_bin, trips, avg_fare_per_mile, median_fare_per_mile FROM q14_results;
