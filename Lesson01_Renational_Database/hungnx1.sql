create database hungnx1;
use hungnx1;

create table roles (
id int auto_increment primary key,
name varchar(45) not null,
description varchar(255));

create table users (
id int auto_increment primary key,
username varchar(45) not null,
password varchar(255) not null,
fullname varchar(255) not null,
phone varchar(100) not null unique,
email varchar(255) not null unique
);

create table users_roles (
user_id int not null,
role_id int not null,
primary key (user_id, role_id),
foreign key (user_id) references users(id),
foreign key (role_id) references roles(id)
);

insert into roles (name, description) values 
('ROLE_ADMIN', 'Quan tri vien'),
('ROLE_USER', 'Nguoi dung')
;

select * from roles;

insert into users (username, password, fullname, phone, email) values
('admin123','123456a@', 'Admin', '0334992975', 'hungnx1@gmail.com')
;

insert into users (username, password, fullname, phone, email) values 
('user123','123456a@', 'User01', '0766088439', 'hungnx2@gmail.com')
;

insert into users (username, password, fullname, phone, email) values 
('user456','123456a@', 'User02', '0945078891', 'hungnx3@gmail.com')
;

select * from users;

insert into users_roles (user_id, role_id) values 
(1,5),
(8,6),
(9,6)
;

select * from users_roles;