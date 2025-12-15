create database QuanLyDiemThi;

use QuanLyDiemThi;

create table HocSinh (
MaHS varchar(20) primary key,
TenHS varchar(50) not null,
NgaySinh datetime not null,
Lop varchar(20),
GT varchar(20));

create table MonHoc (
MaMH varchar(20) primary key,
TenMH varchar(50) not null,
MaGV varchar(20) not null,
foreign key (MaGV) references GiaoVien(MaGV));

create table BangDiem (
MaHS varchar (20) not null,
MaMH varchar(50) not null,
primary key (MaHS, MaMH),
DiemThi int,
NgayKT datetime,
foreign key (MaHS) references HocSinh(MaHS),
foreign key (MaMH) references MonHoc(MaMH));

create table GiaoVien (
MaGV varchar(20) primary key,
TenGV varchar(50) not null,
SDT varchar(10));
