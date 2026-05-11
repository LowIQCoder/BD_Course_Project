USE team27_projectdb;
SET hive.resultset.use.unique.column.names=false;

DROP TABLE IF EXISTS q9_results;
CREATE EXTERNAL TABLE q9_results (
  pickup_lat DOUBLE,
  pickup_lon DOUBLE,
  trips BIGINT,
  avg_fare DOUBLE
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q9';

INSERT OVERWRITE TABLE q9_results
SELECT ROUND(pickup_latitude, 2) AS pickup_lat, ROUND(pickup_longitude, 2) AS pickup_lon, COUNT(*) AS trips, AVG(fare) AS avg_fare
FROM taxi_trips_part_bucket
WHERE fare > 0 AND trip_miles > 0 AND trip_seconds > 0 AND pickup_latitude IS NOT NULL AND pickup_longitude IS NOT NULL
GROUP BY ROUND(pickup_latitude, 2), ROUND(pickup_longitude, 2)
ORDER BY trips DESC
LIMIT 50;

SELECT * FROM q9_results;

INSERT OVERWRITE DIRECTORY 'project/output/q9'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
SELECT pickup_lat, pickup_lon, trips, avg_fare FROM q9_results;
