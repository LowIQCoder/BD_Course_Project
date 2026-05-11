USE team27_projectdb;
SET hive.resultset.use.unique.column.names=false;

DROP TABLE IF EXISTS q6_results;
CREATE EXTERNAL TABLE q6_results (
  payment_type STRING,
  trips BIGINT,
  avg_fare DOUBLE,
  median_fare DOUBLE,
  avg_tip DOUBLE,
  avg_trip_total DOUBLE
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q6';

INSERT OVERWRITE TABLE q6_results
SELECT payment_type, COUNT(*) AS trips, AVG(fare) AS avg_fare, percentile_approx(fare, 0.5) AS median_fare, AVG(tips) AS avg_tip, AVG(trip_total) AS avg_trip_total
FROM taxi_trips_part_bucket
WHERE fare > 0 AND trip_miles > 0 AND trip_seconds > 0 AND payment_type IS NOT NULL
GROUP BY payment_type
ORDER BY trips DESC;

SELECT * FROM q6_results;

INSERT OVERWRITE DIRECTORY 'project/output/q6'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
SELECT payment_type, trips, avg_fare, median_fare, avg_tip, avg_trip_total FROM q6_results;
