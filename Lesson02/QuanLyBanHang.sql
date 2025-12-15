create database QUANLYBANHANG_HUNGNX;

use QUANLYBANHANG_HUNGNX;

CREATE TABLE phieuxuat (
    SoPX VARCHAR(20) PRIMARY KEY,
    NgayXuat DATE NOT NULL
);

CREATE TABLE vattu (
    MaVTU VARCHAR(10) PRIMARY KEY,
    TenVTU VARCHAR(100) NOT NULL
)
;

CREATE TABLE phieunhap (
    SoPN VARCHAR(20) PRIMARY KEY,
    NgayNhap DATE NOT NULL
);

CREATE TABLE dondh (
    SoDH VARCHAR(20) PRIMARY KEY,
    NgayDH DATE NOT NULL,
    MaNCC varchar(10) not null,
    foreign key (MaNCC) references nhacc(MaNCC)
);


CREATE TABLE NHACC (
    MaNCC VARCHAR(10) PRIMARY KEY,
    TenNCC VARCHAR(100) NOT NULL,
    DiaChi VARCHAR(200)
);

create table SDT_NHACC (
MaNCC varchar(10) not null,
SDT varchar(20) not null,
primary key (MaNCC, SDT),
foreign key (MaNCC) references nhacc (MaNCC));





