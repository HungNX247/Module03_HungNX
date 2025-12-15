create database students_db;

use students_db;

create table Class(
id int not null,
name varchar(100) not null);

create table Teacher(
id int,
name varchar(100),
age int,
country text);

insert into Class (id,name) values 
(1, 'Front-end'),
(2, 'Back-end'),
(3, 'Fullstack');

select * from Class;

insert into Teacher (id, name, age, country) values 
(1,'Nguyễn Xuân Hùng', 34, 'Việt Nam'),
(2, 'Tạ Thu Mơ', 27,'Việt Nam'),
(3, 'Jack', 23,'United States');

select * from Teacher;
