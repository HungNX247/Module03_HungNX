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