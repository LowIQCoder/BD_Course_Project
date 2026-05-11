USE team27_projectdb;
SET hive.resultset.use.unique.column.names=false;

DROP TABLE IF EXISTS q13_results;
CREATE EXTERNAL TABLE q13_results (
  hour INT,
  trips BIGINT,
  avg_speed_mph DOUBLE,
  median_speed_mph DOUBLE
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q13';

INSERT OVERWRITE TABLE q13_results
SELECT HOUR(trip_start_timestamp) AS hour, COUNT(*) AS trips, AVG(trip_miles / (trip_seconds / 3600.0)) AS avg_speed_mph, percentile_approx(trip_miles / (trip_seconds / 3600.0), 0.5) AS median_speed_mph
FROM taxi_trips_part_bucket
WHERE fare > 0 AND trip_miles > 0 AND trip_seconds > 0 AND trip_miles / (trip_seconds / 3600.0) BETWEEN 1 AND 80
GROUP BY HOUR(trip_start_timestamp)
ORDER BY hour;

SELECT * FROM q13_results;

INSERT OVERWRITE DIRECTORY 'project/output/q13'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
SELECT hour, trips, avg_speed_mph, median_speed_mph FROM q13_results;
