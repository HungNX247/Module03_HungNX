create database QLSV;

use qlsv;

CREATE TABLE Class (
    ClassID INT AUTO_INCREMENT PRIMARY KEY,
    ClassName VARCHAR(60) NOT NULL,
    StartDate DATE NOT NULL,
    status BIT
);



CREATE TABLE Student (
    StudentID INT AUTO_INCREMENT PRIMARY KEY,
    StudentName VARCHAR(30) NOT NULL,
    Address VARCHAR(50) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    status BIT,
    ClassID INT NOT NULL,
    FOREIGN KEY (ClassID)
        REFERENCES Class (ClassID)
);

CREATE TABLE Subject (
    SubID INT AUTO_INCREMENT PRIMARY KEY,
    SubName VARCHAR(30) NOT NULL,
    Credit TINYINT NOT NULL DEFAULT 1 CHECK (Credit >= 1),
    Status BIT DEFAULT 1
);

CREATE TABLE Mark (
    MarkID INT AUTO_INCREMENT PRIMARY KEY,
    SubID INT NOT NULL,
    StudentID INT NOT NULL,
    Mark FLOAT DEFAULT 0 CHECK (Mark BETWEEN 0 AND 100),
    FOREIGN KEY (SubID)
        REFERENCES Subject (SubID),
    FOREIGN KEY (StudentID)
        REFERENCES Student (StudentID)
);

alter table Mark add column ExamTimes TINYINT DEFAULT 1;

SELECT 
    *
FROM
    Class;
SELECT 
    *
FROM
    Student;
SELECT 
    *
FROM
    Subject;
SELECT 
    *
FROM
    Mark;

insert into Class (ClassName, StartDate, status) values ('A1', '2008-12-20', 1);
insert into Class (ClassName, StartDate, status) values ('A2', '2008-12-22', 1);
insert into Class (ClassName, StartDate, status) values ('B3', current_date, 0);

insert into Student (StudentName, Address, phone, status, ClassID) values ('Hung', 'Ha Noi', '0912113113', 1, 1);
insert into Student (StudentName, Address, phone, status, ClassID) values ('Hoa', 'Hai phong','0987208691', 1, 1);
insert into Student (StudentName, Address, phone, status, ClassID) values ('Manh', 'HCM', '0123123123', 0, 2);

insert into Subject (SubName, Credit, Status) values 
('CF', 5, 1),
('C', 6, 1),
('HDJ', 5, 1),
('RDBMS', 10, 1);

insert into Mark (SubID, StudentID, Mark, ExamTimes) values 
(1, 1, 8, 1),
(1, 2, 10, 2),
(2, 1, 12, 1);
