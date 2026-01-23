create database tcomplex;
use tcomplex;

CREATE TABLE rental_space (
  space_id VARCHAR(10) PRIMARY KEY,
  area DOUBLE NOT NULL,
  status VARCHAR(20) NOT NULL,
  floor INT NOT NULL,
  type VARCHAR(30) NOT NULL,
  description TEXT,
  price BIGINT NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL
);

