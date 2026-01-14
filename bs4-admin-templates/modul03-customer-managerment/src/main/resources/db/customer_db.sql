create database customer_db;
use customer_db;

create table customers (
id int auto_increment primary key,
name varchar(100) not null,
position varchar(45) not null,
office varchar(45) not null,
age int not null,
start_date date not null,
salary decimal not null
);

insert into customers (name, position, office, age, start_date, salary) values
('Nguyen Xuan Hung', 'Front-End', 'HN', 34, '2016-07-01', 20000000),
('Nguyen Xuan Hoan', 'Back-End', 'HP', 35, '2018-10-09', 20000000),
('Nguyen Xuan Hai', 'FullStack', 'CB', 28, '2020-11-01', 20000000);

select * from customers;