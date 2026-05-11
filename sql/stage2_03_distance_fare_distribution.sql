USE team27_projectdb;
SET hive.resultset.use.unique.column.names=false;

DROP TABLE IF EXISTS q3_results;
CREATE EXTERNAL TABLE q3_results (
  distance_bin STRING,
  trips BIGINT,
  avg_fare DOUBLE,
  median_fare DOUBLE
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q3';

INSERT OVERWRITE TABLE q3_results
SELECT
  CASE
    WHEN trip_miles < 1 THEN '0-1'
    WHEN trip_miles < 2 THEN '1-2'
    WHEN trip_miles < 3 THEN '2-3'
    WHEN trip_miles < 5 THEN '3-5'
    WHEN trip_miles < 10 THEN '5-10'
    WHEN trip_miles < 20 THEN '10-20'
    WHEN trip_miles < 50 THEN '20-50'
    ELSE '50-100'
  END AS distance_bin,
  COUNT(*) AS trips,
  AVG(fare) AS avg_fare,
  percentile_approx(fare, 0.5) AS median_fare
FROM taxi_trips_part_bucket
WHERE fare > 0 AND trip_miles > 0 AND trip_seconds > 0
GROUP BY CASE
    WHEN trip_miles < 1 THEN '0-1'
    WHEN trip_miles < 2 THEN '1-2'
    WHEN trip_miles < 3 THEN '2-3'
    WHEN trip_miles < 5 THEN '3-5'
    WHEN trip_miles < 10 THEN '5-10'
    WHEN trip_miles < 20 THEN '10-20'
    WHEN trip_miles < 50 THEN '20-50'
    ELSE '50-100'
  END;

SELECT * FROM q3_results;

INSERT OVERWRITE DIRECTORY 'project/output/q3'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
SELECT distance_bin, trips, avg_fare, median_fare FROM q3_results;
