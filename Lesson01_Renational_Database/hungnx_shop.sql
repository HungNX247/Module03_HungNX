create database hungnx_shop;

use hungnx_shop;

CREATE TABLE products (
    id INT,
    name VARCHAR(100),
    description TEXT,
    price DECIMAL,
    quantity INT
);

insert into products (id, name, description, price, qty) values 
(1, 'Iphone 16', 'Dien thoai Apple', 17000000, 30),
(2, 'Iphone 17 Plus','Dien thoai Apple',20000000, 15),
(3,'Samsung galaxy s25 ultra','Dien thoai Samsung',24000000,40);


UPDATE products 
SET 
    price = 30000000
WHERE
    id = 3;

alter table products modify column id int auto_increment;
alter table products modify column name varchar(100) not null;
alter table products modify column description text not null;
alter table products add constraint ck_products_qty check (qty > 0);

insert into products (name, description, price, qty, link, is_active) values
('Iphone 11', 'Dien thoai Apple', 8500000, 100, '/iphone-x', 2);

alter table products add column link varchar(255);

UPDATE products 
SET 
    link = '/iphone-16'
WHERE
    id = 1;
UPDATE products 
SET 
    link = '/samsung-s25'
WHERE
    id = 2;
UPDATE products 
SET 
    link = '/iphone-17'
WHERE
    id = 3;
UPDATE products 
SET 
    link = '/maytinhbang-apple'
WHERE
    id = 4;

UPDATE products 
SET 
    qty = - 20
WHERE
    id = 1;
UPDATE products 
SET 
    qty = 10
WHERE
    id = 2;
UPDATE products 
SET 
    qty = 11
WHERE
    id = 3;
UPDATE products 
SET 
    qty = 9
WHERE
    id = 4;

UPDATE products 
SET 
    is_active = 1
WHERE
    id = 1;
UPDATE products 
SET 
    is_active = 2
WHERE
    id = 2;
UPDATE products 
SET 
    is_active = 1
WHERE
    id = 3;
UPDATE products 
SET 
    is_active = 1
WHERE
    id = 4;
UPDATE products 
SET 
    link = '/iphone-16'
WHERE
    id = 1;
UPDATE products 
SET 
    qty = 42
WHERE
    id = 6;

alter table products add column is_active int not null;



SELECT 
    *
FROM
    products;
SELECT 
    *
FROM
    products
WHERE
    link <> '/quan-dui';
SELECT 
    *
FROM
    products
WHERE
    price BETWEEN 10000000 AND 18000000;
SELECT 
    *
FROM
    products
WHERE
    description LIKE 'May tinh%';
SELECT 
    *
FROM
    products
WHERE
    description LIKE '%apple';
SELECT 
    *
FROM
    products
WHERE
    description LIKE '%apple%'
        OR name LIKE '%iphone';
SELECT 
    *
FROM
    products
WHERE
    id IN (3 , 4);
SELECT 
    *
FROM
    products
WHERE
    price >= 17000000 AND is_active = 2;
SELECT 
    *
FROM
    products
WHERE
    price >= 20000000 OR is_active = 2;
SELECT 
    *
FROM
    products
WHERE
    NOT is_active = 2;
SELECT 
    *
FROM
    products
WHERE
    (price < 1100000 OR price > 17000000)
        AND qty > 10
        AND NOT is_active = 2;



CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fullname VARCHAR(255) NOT NULL,
    phone VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    address VARCHAR(255) NOT NULL
);

SELECT 
    *
FROM
    customers;

CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customers_id INT NOT NULL,
    order_date DATE NOT NULL,
    total DOUBLE NOT NULL,
    FOREIGN KEY (customers_id)
        REFERENCES customers (id)
);

SELECT 
    *
FROM
    orders;

CREATE TABLE orders_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    qty INT NOT NULL,
    price DOUBLE NOT NULL,
    FOREIGN KEY (order_id)
        REFERENCES orders (id),
    FOREIGN KEY (product_id)
        REFERENCES products (id)
);

SELECT 
    *
FROM
    orders_details;

insert into customers (fullname, phone, email, address) values (
'Nguyễn Xuân Hùng', '0334992975', 'hung@gmail.com', 'Hải Phòng');
insert into customers (fullname, phone, email, address) values (
'Nguyễn Xuân Hải', '0945078891', 'hai@gmail.com', 'Cao Bằng');

insert into orders (customers_id, order_date, total) values (1, current_date(), 20000000);
insert into orders (customers_id, order_date, total) values (2, current_date(), 12000000);
insert into orders_details (order_id, product_id, qty, price) values (1, 3, 1, 20000000);
insert into orders_details (order_id, product_id, qty, price) values (2, 4, 1, 11000000);

-- Inner Join
SELECT 
    o.id AS order_id,
    c.fullname AS customers_name,
    o.order_date,
    o.total AS oder_total
FROM
    customers c
        INNER JOIN
    orders o ON o.customers_id = c.id;

-- LEFT JOIN
SELECT 
    p.id AS product_id,
    p.name AS name,
    od.id AS orders_detail_id,
    od.product_id
FROM
    products p
        LEFT JOIN
    orders_details od ON p.id = od.product_id

