USE team27_projectdb;
SET hive.resultset.use.unique.column.names=false;

DROP TABLE IF EXISTS q8_results;
CREATE EXTERNAL TABLE q8_results (
  var_x STRING,
  var_y STRING,
  corr_value DOUBLE
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
LOCATION 'project/hive/warehouse/q8';

INSERT OVERWRITE TABLE q8_results
SELECT var_x, var_y, ROUND(corr_value, 3)
FROM (
  SELECT
    corr(fare, trip_miles) AS c_0_1,
    corr(fare, CAST(trip_seconds AS DOUBLE)) AS c_0_2,
    corr(fare, trip_seconds / 60.0) AS c_0_3,
    corr(fare, fare / trip_miles) AS c_0_4,
    corr(fare, trip_miles / (trip_seconds / 3600.0)) AS c_0_5,
    corr(fare, tips) AS c_0_6,
    corr(fare, tolls) AS c_0_7,
    corr(fare, extras) AS c_0_8,
    corr(fare, trip_total) AS c_0_9,
    corr(trip_miles, CAST(trip_seconds AS DOUBLE)) AS c_1_2,
    corr(trip_miles, trip_seconds / 60.0) AS c_1_3,
    corr(trip_miles, fare / trip_miles) AS c_1_4,
    corr(trip_miles, trip_miles / (trip_seconds / 3600.0)) AS c_1_5,
    corr(trip_miles, tips) AS c_1_6,
    corr(trip_miles, tolls) AS c_1_7,
    corr(trip_miles, extras) AS c_1_8,
    corr(trip_miles, trip_total) AS c_1_9,
    corr(CAST(trip_seconds AS DOUBLE), trip_seconds / 60.0) AS c_2_3,
    corr(CAST(trip_seconds AS DOUBLE), fare / trip_miles) AS c_2_4,
    corr(CAST(trip_seconds AS DOUBLE), trip_miles / (trip_seconds / 3600.0)) AS c_2_5,
    corr(CAST(trip_seconds AS DOUBLE), tips) AS c_2_6,
    corr(CAST(trip_seconds AS DOUBLE), tolls) AS c_2_7,
    corr(CAST(trip_seconds AS DOUBLE), extras) AS c_2_8,
    corr(CAST(trip_seconds AS DOUBLE), trip_total) AS c_2_9,
    corr(trip_seconds / 60.0, fare / trip_miles) AS c_3_4,
    corr(trip_seconds / 60.0, trip_miles / (trip_seconds / 3600.0)) AS c_3_5,
    corr(trip_seconds / 60.0, tips) AS c_3_6,
    corr(trip_seconds / 60.0, tolls) AS c_3_7,
    corr(trip_seconds / 60.0, extras) AS c_3_8,
    corr(trip_seconds / 60.0, trip_total) AS c_3_9,
    corr(fare / trip_miles, trip_miles / (trip_seconds / 3600.0)) AS c_4_5,
    corr(fare / trip_miles, tips) AS c_4_6,
    corr(fare / trip_miles, tolls) AS c_4_7,
    corr(fare / trip_miles, extras) AS c_4_8,
    corr(fare / trip_miles, trip_total) AS c_4_9,
    corr(trip_miles / (trip_seconds / 3600.0), tips) AS c_5_6,
    corr(trip_miles / (trip_seconds / 3600.0), tolls) AS c_5_7,
    corr(trip_miles / (trip_seconds / 3600.0), extras) AS c_5_8,
    corr(trip_miles / (trip_seconds / 3600.0), trip_total) AS c_5_9,
    corr(tips, tolls) AS c_6_7,
    corr(tips, extras) AS c_6_8,
    corr(tips, trip_total) AS c_6_9,
    corr(tolls, extras) AS c_7_8,
    corr(tolls, trip_total) AS c_7_9,
    corr(extras, trip_total) AS c_8_9
  FROM taxi_trips_part_bucket
  WHERE fare > 0 AND trip_miles > 0 AND trip_seconds > 0
) corrs
LATERAL VIEW stack(100, 'fare', 'fare', CAST(1.0 AS DOUBLE), 'fare', 'trip_miles', c_0_1, 'fare', 'trip_seconds', c_0_2, 'fare', 'duration_minutes', c_0_3, 'fare', 'fare_per_mile', c_0_4, 'fare', 'speed_mph', c_0_5, 'fare', 'tips', c_0_6, 'fare', 'tolls', c_0_7, 'fare', 'extras', c_0_8, 'fare', 'trip_total', c_0_9, 'trip_miles', 'fare', c_0_1, 'trip_miles', 'trip_miles', CAST(1.0 AS DOUBLE), 'trip_miles', 'trip_seconds', c_1_2, 'trip_miles', 'duration_minutes', c_1_3, 'trip_miles', 'fare_per_mile', c_1_4, 'trip_miles', 'speed_mph', c_1_5, 'trip_miles', 'tips', c_1_6, 'trip_miles', 'tolls', c_1_7, 'trip_miles', 'extras', c_1_8, 'trip_miles', 'trip_total', c_1_9, 'trip_seconds', 'fare', c_0_2, 'trip_seconds', 'trip_miles', c_1_2, 'trip_seconds', 'trip_seconds', CAST(1.0 AS DOUBLE), 'trip_seconds', 'duration_minutes', c_2_3, 'trip_seconds', 'fare_per_mile', c_2_4, 'trip_seconds', 'speed_mph', c_2_5, 'trip_seconds', 'tips', c_2_6, 'trip_seconds', 'tolls', c_2_7, 'trip_seconds', 'extras', c_2_8, 'trip_seconds', 'trip_total', c_2_9, 'duration_minutes', 'fare', c_0_3, 'duration_minutes', 'trip_miles', c_1_3, 'duration_minutes', 'trip_seconds', c_2_3, 'duration_minutes', 'duration_minutes', CAST(1.0 AS DOUBLE), 'duration_minutes', 'fare_per_mile', c_3_4, 'duration_minutes', 'speed_mph', c_3_5, 'duration_minutes', 'tips', c_3_6, 'duration_minutes', 'tolls', c_3_7, 'duration_minutes', 'extras', c_3_8, 'duration_minutes', 'trip_total', c_3_9, 'fare_per_mile', 'fare', c_0_4, 'fare_per_mile', 'trip_miles', c_1_4, 'fare_per_mile', 'trip_seconds', c_2_4, 'fare_per_mile', 'duration_minutes', c_3_4, 'fare_per_mile', 'fare_per_mile', CAST(1.0 AS DOUBLE), 'fare_per_mile', 'speed_mph', c_4_5, 'fare_per_mile', 'tips', c_4_6, 'fare_per_mile', 'tolls', c_4_7, 'fare_per_mile', 'extras', c_4_8, 'fare_per_mile', 'trip_total', c_4_9, 'speed_mph', 'fare', c_0_5, 'speed_mph', 'trip_miles', c_1_5, 'speed_mph', 'trip_seconds', c_2_5, 'speed_mph', 'duration_minutes', c_3_5, 'speed_mph', 'fare_per_mile', c_4_5, 'speed_mph', 'speed_mph', CAST(1.0 AS DOUBLE), 'speed_mph', 'tips', c_5_6, 'speed_mph', 'tolls', c_5_7, 'speed_mph', 'extras', c_5_8, 'speed_mph', 'trip_total', c_5_9, 'tips', 'fare', c_0_6, 'tips', 'trip_miles', c_1_6, 'tips', 'trip_seconds', c_2_6, 'tips', 'duration_minutes', c_3_6, 'tips', 'fare_per_mile', c_4_6, 'tips', 'speed_mph', c_5_6, 'tips', 'tips', CAST(1.0 AS DOUBLE), 'tips', 'tolls', c_6_7, 'tips', 'extras', c_6_8, 'tips', 'trip_total', c_6_9, 'tolls', 'fare', c_0_7, 'tolls', 'trip_miles', c_1_7, 'tolls', 'trip_seconds', c_2_7, 'tolls', 'duration_minutes', c_3_7, 'tolls', 'fare_per_mile', c_4_7, 'tolls', 'speed_mph', c_5_7, 'tolls', 'tips', c_6_7, 'tolls', 'tolls', CAST(1.0 AS DOUBLE), 'tolls', 'extras', c_7_8, 'tolls', 'trip_total', c_7_9, 'extras', 'fare', c_0_8, 'extras', 'trip_miles', c_1_8, 'extras', 'trip_seconds', c_2_8, 'extras', 'duration_minutes', c_3_8, 'extras', 'fare_per_mile', c_4_8, 'extras', 'speed_mph', c_5_8, 'extras', 'tips', c_6_8, 'extras', 'tolls', c_7_8, 'extras', 'extras', CAST(1.0 AS DOUBLE), 'extras', 'trip_total', c_8_9, 'trip_total', 'fare', c_0_9, 'trip_total', 'trip_miles', c_1_9, 'trip_total', 'trip_seconds', c_2_9, 'trip_total', 'duration_minutes', c_3_9, 'trip_total', 'fare_per_mile', c_4_9, 'trip_total', 'speed_mph', c_5_9, 'trip_total', 'tips', c_6_9, 'trip_total', 'tolls', c_7_9, 'trip_total', 'extras', c_8_9, 'trip_total', 'trip_total', CAST(1.0 AS DOUBLE)) s AS var_x, var_y, corr_value;

SELECT * FROM q8_results;

INSERT OVERWRITE DIRECTORY 'project/output/q8'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
SELECT var_x, var_y, corr_value FROM q8_results;
