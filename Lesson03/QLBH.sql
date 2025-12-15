create database QLBH03;

use QLBH03;

create table customer (
cID int auto_increment primary key,
cName varchar(50) not null,
cAge int check (cAge > 0));

create table `Order` (
oID int auto_increment primary key,
cID int not null,
oDate date not null,
oTotalPrice decimal(10,2),
foreign key (cID) references customer(cID));

create table Product (
pID int auto_increment primary key,
pName varchar(50) not null,
pPrice decimal(10,2) not null check (pPrice >= 0));

create table OderDetail (
oID int not null,
pID int not null,
odQTY int not null check (odQTY > 0),
foreign key (oID) references `Order`(oID),
foreign key (pID) references Product(pID));

select * from customer;
select * from `Order`;
select * from Product;
select * from OderDetail;

insert into customer (cName, cAge) values 
('Minh Quan', 10),
('Ngoc Oanh', 20),
('Hong Ha', 50);

insert into `Order` (cID, oDate, oTotalPrice) values 
(1, '2006-03-21', null),
(2, '2006-03-23', null),
(1, '2006-03-16', null);

insert into Product (pName, pPrice) values 
('May Giat', 3),
('Tu Lanh', 5),
('Dieu Hoa', 7),
('Quat', 1),
('Bep Dien', 2);

insert into OderDetail (oID, pID, odQTY) values 
(1,1,3),
(1,3,7),
(1,4,2),
(2,1,1),
(3,1,8),
(2,5,4),
(2,3,3);

select oID, oDate, oTotalPrice from `order`;

select distinct c.cID, c.cName as CustomerName, p.pName as ProductName from customer c 
join`order` o on c.cID = o.cID 
join oderdetail od on o.oID = od.oID 
join product p on od.pID = p.pID;

select c.cID, c.cName from customer c left join `order` o on c.cID = o.cID where o.cID is null;

select o.oID, o.oDate, sum(od.odQTY * p.pPrice) as TotalPrice from `order` o 
join oderdetail od on o.oID = od.oID 
join product p on od.pID = p.pID 
group by o.oID, o.oDate;
