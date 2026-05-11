USE team27_projectdb;
SET hive.resultset.use.unique.column.names=false;

DROP TABLE IF EXISTS q1_results;
CREATE EXTERNAL TABLE q1_results (
  month INT,
  trips BIGINT,
  avg_fare DOUBLE,
  avg_trip_miles DOUBLE
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q1';

INSERT OVERWRITE TABLE q1_results
SELECT MONTH(trip_start_timestamp) AS month, COUNT(*) AS trips, AVG(fare) AS avg_fare, AVG(trip_miles) AS avg_trip_miles
FROM taxi_trips_part_bucket
WHERE fare > 0 AND trip_miles > 0 AND trip_seconds > 0
GROUP BY MONTH(trip_start_timestamp)
ORDER BY month;

SELECT * FROM q1_results;

INSERT OVERWRITE DIRECTORY 'project/output/q1'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
SELECT month, trips, avg_fare, avg_trip_miles FROM q1_results;
