create database hungnx_shop;

use hungnx_shop;

create table products (
id int,
name varchar(100),
description text,
price decimal,
quantity int);

insert into products (id, name, description, price, quantity) values 
(1, 'Iphone 16', 'Dien thoai Apple', 17000000, 30),
(2, 'Iphone 17 Plus','Dien thoai Apple',20000000, 15),
(3,'Samsung galaxy s25 ultra','Dien thoai Samsung',24000000,40);

select * from products;

update products 
set price = 30000000 where id = 3;

create table customers (
id int,
name varchar(100),
birthday datetime,
address text,
phone int);

-- drop table customers;

insert into customers (id,name, birthday, address, phone) values 
(1, 'Nguyễn Xuân Hùng', '1991-06-28','Hải Phòng', '0766088439'),
(2, 'Tạ Thu Mơ','1998-10-07','Hà Nội','0334992975');

select * from users;

delete from customers where id = 1;

update customers set birthday = '1998-10-08' where id = 2;

alter table customers add email text;
alter table customers add gender varchar(100),
add details text;

alter table customers drop column details;

alter table customers change column name fullname varchar(100);

alter table customers modify phone varchar(20);

alter table customers rename to users;

alter table users add primary key (id);