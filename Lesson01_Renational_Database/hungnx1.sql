create database hungnx1;
use hungnx1;

CREATE TABLE roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(255) NOT NULL,
    fullname VARCHAR(255) NOT NULL,
    phone VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE users_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id , role_id),
    FOREIGN KEY (user_id)
        REFERENCES users (id),
    FOREIGN KEY (role_id)
        REFERENCES roles (id)
);

insert into roles (name, description) values 
('ROLE_ADMIN', 'Quan tri vien'),
('ROLE_USER', 'Nguoi dung')
;

SELECT 
    *
FROM
    roles;

insert into users (username, password, fullname, phone, email) values
('admin123','123456a@', 'Admin', '0334992975', 'hungnx1@gmail.com')
;

insert into users (username, password, fullname, phone, email) values 
('user123','123456a@', 'User01', '0766088439', 'hungnx2@gmail.com')
;

insert into users (username, password, fullname, phone, email) values 
('user456','123456a@', 'User02', '0945078891', 'hungnx3@gmail.com')
;

SELECT 
    *
FROM
    users;

insert into users_roles (user_id, role_id) values 
(1,5),
(8,6),
(9,6)
;

SELECT 
    *
FROM
    users_roles;