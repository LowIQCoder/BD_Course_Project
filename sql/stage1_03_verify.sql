SET search_path TO chicago_taxi, public;

SELECT 'staging_raw_trips' AS table_name, COUNT(*) AS row_count FROM staging_raw_trips
UNION ALL
SELECT 'dim_taxi', COUNT(*) FROM dim_taxi
UNION ALL
SELECT 'dim_company', COUNT(*) FROM dim_company
UNION ALL
SELECT 'dim_payment_type', COUNT(*) FROM dim_payment_type
UNION ALL
SELECT 'fact_trips', COUNT(*) FROM fact_trips;

SELECT MIN(trip_start_timestamp) AS min_start_ts,
       MAX(trip_start_timestamp) AS max_start_ts
FROM fact_trips;

SELECT COUNT(*) AS invalid_end_ts_rows
FROM fact_trips
WHERE is_valid_end_timestamp = FALSE;

SELECT COUNT(*) AS invalid_duration_rows
FROM fact_trips
WHERE is_valid_duration = FALSE;