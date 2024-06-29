CREATE TABLE person
(
    id SERIAL PRIMARY KEY,
    name TEXT,
    age SERIAL,
    driver_id BOOLEAN,
    car_id SERIAL REFERENCES car (id)
)

CREATE TABLE car
(
id SERIAL PRIMARY KEY,
brand TEXT,
model TEXT,
price SERIAL
)