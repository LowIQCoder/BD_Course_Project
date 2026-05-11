USE team27_projectdb;
SET hive.resultset.use.unique.column.names=false;

DROP TABLE IF EXISTS q2_results;
CREATE EXTERNAL TABLE q2_results (
  weekday_name STRING,
  hour INT,
  trips BIGINT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q2';

INSERT OVERWRITE TABLE q2_results
SELECT date_format(trip_start_timestamp, 'EEEE') AS weekday_name, HOUR(trip_start_timestamp) AS hour, COUNT(*) AS trips
FROM taxi_trips_part_bucket
WHERE fare > 0 AND trip_miles > 0 AND trip_seconds > 0
GROUP BY date_format(trip_start_timestamp, 'EEEE'), HOUR(trip_start_timestamp);

SELECT * FROM q2_results;

INSERT OVERWRITE DIRECTORY 'project/output/q2'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
SELECT weekday_name, hour, trips FROM q2_results;
