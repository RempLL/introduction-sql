CREATE TABLE cars
(
    id BIGSERIAL PRIMARY KEY,
    brand VARCHAR(20)             NOT NULL,
    model VARCHAR(50)             NOT NULL,
    price INT CHECK ( price > 0 ) NOT NULL
);

CREATE TABLE owners
(
    id BIGSERIAL PRIMARY KEY,
    name    VARCHAR(255)                NOT NULL,
    age     INT CHECK ( age > 18 )      NOT NULL,
    license BOOLEAN DEFAULT TRUE        NOT NULL,
    car_id  BIGINT REFERENCES cars (id) NOT NULL
);