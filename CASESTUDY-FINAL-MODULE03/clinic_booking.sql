create database clinic_booking;
use clinic_booking;

create table users (
id int auto_increment primary key,
full_name varchar(100) not null,
phone varchar (15) not null unique,
password_hash varchar(255) not null,
role enum('PATIENT','ADMIN') not null default 'PATIENT',
created_at timestamp default current_timestamp
);

create table specialties (
id int auto_increment primary key,
name varchar(100) not null unique);

create table doctors (
id int auto_increment primary key,
full_name varchar(100) not null,
specialty_id INT NOT NULL,
phone varchar(20),
price int not null default 200000,
foreign key (specialty_id) references specialties(id)
);

create table appointments (
id int primary key auto_increment,
 patient_id int not null,
 doctor_id int not null,
 appointment_date date not null,
 appointment_time time not null,
 status enum ('BOOKED','CANCELED','DONE') not null default 'BOOKED',
 note varchar(255),
 created_at timestamp default current_timestamp,
 
 foreign key (patient_id) references users(id),
 foreign key (doctor_id) references doctors(id),
 
 unique key unique_booking (doctor_id, appointment_date,appointment_time)
);

INSERT INTO specialties(name) VALUES
('Nội tổng quát'),
('Tai Mũi Họng'),
('Da liễu'),
('Tim mạch'),
('Nhi khoa');

INSERT INTO doctors(full_name, specialty_id, phone, price) VALUES
('BS. Nguyễn Văn Nghĩa', 1, '0987208691', 250000),
('BS. Trần Thị Bình', 2, '0945078891', 300000),
('BS. Vương Tiến Việt', 3, '0945378891', 350000),
('BS. Lê Tân', 4, '0766088439', 400000),
('BS. Vũ Đức Huy', 5, '0334992975', 280000);



INSERT INTO users(full_name, phone, password_hash, role) VALUES
('Admin System', '0987208692', '$2a$10$uV8uWZf7Qh0eGqKxY6mVfOq0xgU3Vd8m0o4mOq2QdTjKxFq7xqG9y', 'ADMIN'),
('Nguyễn Văn Phát', '0981540683', '$2a$10$uV8uWZf7Qh0eGqKxY6mVfOq0xgU3Vd8m0o4mOq2QdTjKxFq7xqG9y', 'PATIENT');

select * from users;
UPDATE users
SET password_hash = '$2a$10$V1110HsfImMc5vHXJYmxpuy8imbfGcRZOVp13tqsgYMBvCHK1ecwO'
WHERE phone='0987208692';
