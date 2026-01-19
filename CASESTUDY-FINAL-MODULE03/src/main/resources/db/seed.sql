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