USE team27_projectdb;
SET hive.resultset.use.unique.column.names=false;

DROP TABLE IF EXISTS q7_results;
CREATE EXTERNAL TABLE q7_results (
  fare_bin STRING,
  trips BIGINT
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q7';

INSERT OVERWRITE TABLE q7_results
SELECT
  CASE
    WHEN fare < 5 THEN '0-5'
    WHEN fare < 10 THEN '5-10'
    WHEN fare < 15 THEN '10-15'
    WHEN fare < 20 THEN '15-20'
    WHEN fare < 30 THEN '20-30'
    WHEN fare < 50 THEN '30-50'
    WHEN fare < 100 THEN '50-100'
    ELSE '100+'
  END AS fare_bin,
  COUNT(*) AS trips
FROM taxi_trips_part_bucket
WHERE fare > 0 AND trip_miles > 0 AND trip_seconds > 0
GROUP BY CASE
    WHEN fare < 5 THEN '0-5'
    WHEN fare < 10 THEN '5-10'
    WHEN fare < 15 THEN '10-15'
    WHEN fare < 20 THEN '15-20'
    WHEN fare < 30 THEN '20-30'
    WHEN fare < 50 THEN '30-50'
    WHEN fare < 100 THEN '50-100'
    ELSE '100+'
  END;

SELECT * FROM q7_results;

INSERT OVERWRITE DIRECTORY 'project/output/q7'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
SELECT fare_bin, trips FROM q7_results;
