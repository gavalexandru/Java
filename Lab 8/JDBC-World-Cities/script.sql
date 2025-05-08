CREATE TABLE continents (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE countries (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    code VARCHAR(3) NOT NULL UNIQUE,
    continent_id INTEGER NOT NULL,
    CONSTRAINT fk_continent
        FOREIGN KEY (continent_id) 
        REFERENCES continents(id)
        ON DELETE RESTRICT
);

CREATE TABLE cities
(
    id  SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    country_id INTEGER NOT NULL,
    is_capital BOOLEAN NOT NULL DEFAULT FALSE,
    latitude   DECIMAL(10, 8) NOT NULL,
    longitude  DECIMAL(11, 8) NOT NULL,
    CONSTRAINT fk_country
        FOREIGN KEY (country_id)
        REFERENCES countries (id)
        ON DELETE RESTRICT
);