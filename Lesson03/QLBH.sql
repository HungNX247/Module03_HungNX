create database QLBH03;

use QLBH03;

CREATE TABLE customer (
    cID INT AUTO_INCREMENT PRIMARY KEY,
    cName VARCHAR(50) NOT NULL,
    cAge INT CHECK (cAge > 0)
);

CREATE TABLE `Order` (
    oID INT AUTO_INCREMENT PRIMARY KEY,
    cID INT NOT NULL,
    oDate DATE NOT NULL,
    oTotalPrice DECIMAL(10 , 2 ),
    FOREIGN KEY (cID)
        REFERENCES customer (cID)
);

CREATE TABLE Product (
    pID INT AUTO_INCREMENT PRIMARY KEY,
    pName VARCHAR(50) NOT NULL,
    pPrice DECIMAL(10 , 2 ) NOT NULL CHECK (pPrice >= 0)
);

CREATE TABLE OderDetail (
    oID INT NOT NULL,
    pID INT NOT NULL,
    odQTY INT NOT NULL CHECK (odQTY > 0),
    FOREIGN KEY (oID)
        REFERENCES `Order` (oID),
    FOREIGN KEY (pID)
        REFERENCES Product (pID)
);

SELECT 
    *
FROM
    customer;
SELECT 
    *
FROM
    `Order`;
SELECT 
    *
FROM
    Product;
SELECT 
    *
FROM
    OderDetail;

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

SELECT 
    oID, oDate, oTotalPrice
FROM
    `order`;

SELECT DISTINCT
    c.cID, c.cName AS CustomerName, p.pName AS ProductName
FROM
    customer c
        JOIN
    `order` o ON c.cID = o.cID
        JOIN
    oderdetail od ON o.oID = od.oID
        JOIN
    product p ON od.pID = p.pID;

SELECT 
    c.cID, c.cName
FROM
    customer c
        LEFT JOIN
    `order` o ON c.cID = o.cID
WHERE
    o.cID IS NULL;

SELECT 
    o.oID, o.oDate, SUM(od.odQTY * p.pPrice) AS TotalPrice
FROM
    `order` o
        JOIN
    oderdetail od ON o.oID = od.oID
        JOIN
    product p ON od.pID = p.pID
GROUP BY o.oID , o.oDate;
