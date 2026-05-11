USE team27_projectdb;
SET hive.resultset.use.unique.column.names=false;

DROP TABLE IF EXISTS q10_results;
CREATE EXTERNAL TABLE q10_results (
  pickup_community_area DOUBLE,
  dropoff_community_area DOUBLE,
  trips BIGINT,
  avg_fare DOUBLE,
  avg_trip_miles DOUBLE
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q10';

INSERT OVERWRITE TABLE q10_results
SELECT pickup_community_area, dropoff_community_area, COUNT(*) AS trips, AVG(fare) AS avg_fare, AVG(trip_miles) AS avg_trip_miles
FROM taxi_trips_part_bucket
WHERE fare > 0 AND trip_miles > 0 AND trip_seconds > 0 AND pickup_community_area IS NOT NULL AND dropoff_community_area IS NOT NULL
GROUP BY pickup_community_area, dropoff_community_area
ORDER BY trips DESC
LIMIT 20;

SELECT * FROM q10_results;

INSERT OVERWRITE DIRECTORY 'project/output/q10'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
SELECT pickup_community_area, dropoff_community_area, trips, avg_fare, avg_trip_miles FROM q10_results;
