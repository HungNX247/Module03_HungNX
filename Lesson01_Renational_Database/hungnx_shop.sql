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