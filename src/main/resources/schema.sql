CREATE TABLE IF NOT EXISTS temperature_readings (
    id SERIAL PRIMARY KEY,
    device_name VARCHAR(50) NOT NULL,
    location VARCHAR(100) NOT NULL,
    temperature DECIMAL(5,2),
    reading_time TIMESTAMP NOT NULL
);