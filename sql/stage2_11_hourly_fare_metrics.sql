USE team27_projectdb;
SET hive.resultset.use.unique.column.names=false;

DROP TABLE IF EXISTS q11_results;
CREATE EXTERNAL TABLE q11_results (
  hour INT,
  trips BIGINT,
  avg_fare DOUBLE,
  median_fare DOUBLE
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q11';

INSERT OVERWRITE TABLE q11_results
SELECT HOUR(trip_start_timestamp) AS hour, COUNT(*) AS trips, AVG(fare) AS avg_fare, percentile_approx(fare, 0.5) AS median_fare
FROM taxi_trips_part_bucket
WHERE fare > 0 AND trip_miles > 0 AND trip_seconds > 0
GROUP BY HOUR(trip_start_timestamp)
ORDER BY hour;

SELECT * FROM q11_results;

INSERT OVERWRITE DIRECTORY 'project/output/q11'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
SELECT hour, trips, avg_fare, median_fare FROM q11_results;
