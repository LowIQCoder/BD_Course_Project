CREATE SCHEMA IF NOT EXISTS chicago_taxi;
SET search_path TO chicago_taxi, public;

DROP TABLE IF EXISTS fact_trips CASCADE;
DROP TABLE IF EXISTS staging_raw_trips CASCADE;
DROP TABLE IF EXISTS dim_payment_type CASCADE;
DROP TABLE IF EXISTS dim_company CASCADE;
DROP TABLE IF EXISTS dim_taxi CASCADE;

CREATE UNLOGGED TABLE staging_raw_trips (
    taxi_id                 INTEGER,
    trip_start_timestamp    TIMESTAMP,
    trip_end_timestamp      TIMESTAMP,
    trip_seconds            INTEGER,
    trip_miles              NUMERIC(12,2),
    pickup_census_tract     INTEGER,
    dropoff_census_tract    INTEGER,
    pickup_community_area   INTEGER,
    dropoff_community_area  INTEGER,
    fare                    NUMERIC(12,2),
    tips                    NUMERIC(12,2),
    tolls                   NUMERIC(12,2),
    extras                  NUMERIC(12,2),
    trip_total              NUMERIC(12,2),
    payment_type            TEXT,
    company                 INTEGER,
    pickup_latitude         INTEGER,
    pickup_longitude        INTEGER,
    dropoff_latitude        INTEGER,
    dropoff_longitude       INTEGER
);

CREATE TABLE dim_taxi (
    taxi_id INTEGER PRIMARY KEY
);

CREATE TABLE dim_company (
    company_code INTEGER PRIMARY KEY
);

CREATE TABLE dim_payment_type (
    payment_type TEXT PRIMARY KEY
);

CREATE TABLE fact_trips (
    trip_id                   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    taxi_id                   INTEGER NOT NULL,
    company_code              INTEGER,
    payment_type              TEXT NOT NULL,
    trip_start_timestamp      TIMESTAMP NOT NULL,
    trip_end_timestamp        TIMESTAMP,
    trip_seconds              INTEGER,
    trip_miles                NUMERIC(12,2),
    pickup_census_tract       INTEGER,
    dropoff_census_tract      INTEGER,
    pickup_community_area     INTEGER,
    dropoff_community_area    INTEGER,
    fare                      NUMERIC(12,2),
    tips                      NUMERIC(12,2),
    tolls                     NUMERIC(12,2),
    extras                    NUMERIC(12,2),
    trip_total                NUMERIC(12,2),
    pickup_latitude_code      INTEGER,
    pickup_longitude_code     INTEGER,
    dropoff_latitude_code     INTEGER,
    dropoff_longitude_code    INTEGER,
    trip_year                 INTEGER,
    trip_month                INTEGER,
    trip_day                  INTEGER,
    trip_hour                 INTEGER,
    trip_weekday              INTEGER,
    is_valid_end_timestamp    BOOLEAN NOT NULL,
    is_valid_duration         BOOLEAN NOT NULL,
    is_valid_amounts          BOOLEAN NOT NULL
);



ALTER TABLE fact_trips
ADD CONSTRAINT fk_fact_taxi
FOREIGN KEY (taxi_id)
REFERENCES dim_taxi(taxi_id);


CREATE INDEX idx_fact_trips_start_ts ON fact_trips (trip_start_timestamp);
CREATE INDEX idx_fact_trips_payment_type ON fact_trips (payment_type);
CREATE INDEX idx_fact_trips_company_code ON fact_trips (company_code);
CREATE INDEX idx_fact_trips_trip_month ON fact_trips (trip_month);
