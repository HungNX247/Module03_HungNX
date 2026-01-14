create database user_db;
use user_db;

create table users (
id int auto_increment primary key,
username varchar(50) not null,
password varchar(255) not null,
roles varchar(20));

insert into users (username, password, roles) values 
('admin','123','ADMIN'),
('user','123','USER');

select * from users;