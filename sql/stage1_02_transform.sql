SET search_path TO chicago_taxi, public;

INSERT INTO dim_taxi (taxi_id)
SELECT DISTINCT taxi_id
FROM staging_raw_trips
WHERE taxi_id IS NOT NULL;

INSERT INTO dim_company (company_code)
SELECT DISTINCT company
FROM staging_raw_trips
WHERE company IS NOT NULL;

INSERT INTO dim_payment_type (payment_type)
SELECT DISTINCT payment_type
FROM staging_raw_trips
WHERE payment_type IS NOT NULL
  AND TRIM(payment_type) <> '';

INSERT INTO fact_trips (
    taxi_id,
    company_code,
    payment_type,
    trip_start_timestamp,
    trip_end_timestamp,
    trip_seconds,
    trip_miles,
    pickup_census_tract,
    dropoff_census_tract,
    pickup_community_area,
    dropoff_community_area,
    fare,
    tips,
    tolls,
    extras,
    trip_total,
    pickup_latitude_code,
    pickup_longitude_code,
    dropoff_latitude_code,
    dropoff_longitude_code,
    trip_year,
    trip_month,
    trip_day,
    trip_hour,
    trip_weekday,
    is_valid_end_timestamp,
    is_valid_duration,
    is_valid_amounts
)
SELECT
    s.taxi_id,
    s.company,
    s.payment_type,
    s.trip_start_timestamp,
    CASE
        WHEN s.trip_end_timestamp IS NULL THEN NULL
        WHEN s.trip_end_timestamp < TIMESTAMP '2000-01-01 00:00:00' THEN NULL
        ELSE s.trip_end_timestamp
    END,
    s.trip_seconds,
    s.trip_miles,
    s.pickup_census_tract,
    s.dropoff_census_tract,
    s.pickup_community_area,
    s.dropoff_community_area,
    s.fare,
    s.tips,
    s.tolls,
    s.extras,
    s.trip_total,
    s.pickup_latitude,
    s.pickup_longitude,
    s.dropoff_latitude,
    s.dropoff_longitude,
    EXTRACT(YEAR FROM s.trip_start_timestamp)::INTEGER,
    EXTRACT(MONTH FROM s.trip_start_timestamp)::INTEGER,
    EXTRACT(DAY FROM s.trip_start_timestamp)::INTEGER,
    EXTRACT(HOUR FROM s.trip_start_timestamp)::INTEGER,
    EXTRACT(DOW FROM s.trip_start_timestamp)::INTEGER,
    CASE
        WHEN s.trip_end_timestamp IS NOT NULL
         AND s.trip_end_timestamp >= TIMESTAMP '2000-01-01 00:00:00'
        THEN TRUE
        ELSE FALSE
    END,
    CASE
        WHEN s.trip_seconds IS NOT NULL
         AND s.trip_seconds > 0
        THEN TRUE
        ELSE FALSE
    END,
    CASE
        WHEN COALESCE(s.fare, 0) >= 0
         AND COALESCE(s.tips, 0) >= 0
         AND COALESCE(s.tolls, 0) >= 0
         AND COALESCE(s.extras, 0) >= 0
         AND COALESCE(s.trip_total, 0) >= 0
        THEN TRUE
        ELSE FALSE
    END
FROM staging_raw_trips s
WHERE s.taxi_id IS NOT NULL
  AND s.trip_start_timestamp IS NOT NULL
  AND s.payment_type IS NOT NULL
  AND TRIM(s.payment_type) <> '';

ANALYZE staging_raw_trips;
ANALYZE dim_taxi;
ANALYZE dim_company;
ANALYZE dim_payment_type;
ANALYZE fact_trips;