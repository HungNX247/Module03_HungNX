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

insert into Mark (SubID, StudentID, Mark, ExamTimes) values 
(4, 3, 7, 1),
(3, 2, 9, 2),
(2, 3, 10, 1),
(4, 1, 9, 1);

SELECT 
    *
FROM
    student;
SELECT 
    *
FROM
    class;
SELECT 
    *
FROM
    subject;
SELECT 
    *
FROM
    Mark;

UPDATE class 
SET 
    StartDate = '2025-10-07'
WHERE
    ClassID = 2;


SELECT 
    *
FROM
    student
WHERE
    status = TRUE;
SELECT 
    *
FROM
    subject
WHERE
    Credit < 10;

SELECT 
    S.StudentID, S.StudentName, C.ClassName
FROM
    student S
        JOIN
    class C ON S.ClassID = C.ClassID
WHERE
    C.ClassName = 'A1';

SELECT 
    S.StudentID, S.StudentName, Sub.SubName, M.mark
FROM
    student S
        JOIN
    Mark M ON S.StudentID = M.StudentID
        JOIN
    subject Sub ON M.SubID = Sub.SubID
WHERE
    Sub.SubName = 'CF';
    
SELECT 
    *
FROM
    student;
    
SELECT 
    *
FROM
    student
WHERE
    LOWER(StudentName) LIKE 'h%';

SELECT 
    *
FROM
    class
WHERE
    MONTH(StartDate) = 12;

SELECT 
    *
FROM
    subject
WHERE
    (Credit >= 3 AND Credit <= 5);

UPDATE student 
SET 
    ClassID = 2
WHERE
    StudentName = 'Hung';

SELECT 
    s.StudentName AS studentname,
    sub.SubName AS subname,
    m.mark AS mark
FROM
    mark m
        JOIN
    student s ON m.StudentID = s.StudentID
        JOIN
    subject sub ON m.SubID = sub.SubID
ORDER BY m.Mark DESC , s.StudentName ASC;

select * from mark;
select * from student;
select * from subject;
select * from class;

SELECT 
    Address, COUNT(StudentID) AS 'Số lượng học viên'
FROM
    student
GROUP BY Address;

SELECT 
    S.StudentID, S.StudentName, AVG(mark)
FROM
    student S
        JOIN
    mark M ON S.StudentID = M.StudentID
GROUP BY S.StudentID , S.StudentName;

SELECT 
    S.StudentID, S.StudentName, AVG(mark)
FROM
    student S
        JOIN
    mark M ON S.StudentID = M.StudentID
GROUP BY S.StudentID , S.StudentName
HAVING AVG(mark) > 15;

SELECT 
    S.StudentID, S.StudentName, AVG(mark)
FROM
    student S
        JOIN
    mark M ON S.StudentID = M.StudentID
GROUP BY S.StudentID , S.StudentName
HAVING AVG(mark) >= ALL (SELECT 
        AVG(mark)
    FROM
        mark
    GROUP BY mark.StudentID);

select Subname, max(Credit) as 'Số lượng học phần' from subject 
group by Subname;

SELECT 
    sub.SubID AS id, sub.SubName, MAX(mark)
FROM
    subject sub
        JOIN
    mark M ON sub.SubID = M.SubID
GROUP BY sub.SubID , sub.Subname;

SELECT 
    S.StudentID, S.StudentName, AVG(mark)
FROM
    student S
        JOIN
    mark M ON S.StudentID = M.StudentID
GROUP BY S.StudentID , S.StudentName
ORDER BY AVG(mark) DESC;