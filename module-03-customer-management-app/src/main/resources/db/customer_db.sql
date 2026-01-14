create database customer_db;

use customer_db;

create table customers(
    id int auto_increment primary key,
    name varchar(100) not null,
    position varchar(45) not null,
    office varchar(45) not null,
    age int not null,
    start_date date not null,
    salary decimal not null
);

insert into customers(name, position, office, age, start_date, salary) values
    ('Le Trang Ty', 'Frontend Developer', 'HN', 18, '2025-01-01', 10000),
    ('Dinh Thi Suu', 'Backend Developer', 'DN', 20, '2025-01-01', 10000),
    ('Nguyen Van Dan', 'Backend Developer', 'HCM', 19, '2025-01-01', 10000);