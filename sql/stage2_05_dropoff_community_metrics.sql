USE team27_projectdb;
SET hive.resultset.use.unique.column.names=false;

DROP TABLE IF EXISTS q5_results;
CREATE EXTERNAL TABLE q5_results (
  dropoff_community_area DOUBLE,
  trips BIGINT,
  avg_fare DOUBLE,
  avg_trip_miles DOUBLE
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q5';

INSERT OVERWRITE TABLE q5_results
SELECT dropoff_community_area, COUNT(*) AS trips, AVG(fare) AS avg_fare, AVG(trip_miles) AS avg_trip_miles
FROM taxi_trips_part_bucket
WHERE fare > 0 AND trip_miles > 0 AND trip_seconds > 0 AND dropoff_community_area IS NOT NULL
GROUP BY dropoff_community_area
ORDER BY trips DESC
LIMIT 15;

SELECT * FROM q5_results;

INSERT OVERWRITE DIRECTORY 'project/output/q5'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
SELECT dropoff_community_area, trips, avg_fare, avg_trip_miles FROM q5_results;
