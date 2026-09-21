CREATE DATABASE IF NOT EXISTS library_management;
USE library_management;

CREATE TABLE IF NOT EXISTS books (
 book_id INT AUTO_INCREMENT PRIMARY KEY,
 title VARCHAR(150) NOT NULL,
 author VARCHAR(100) NOT NULL,
 category VARCHAR(80) NOT NULL,
 quantity INT NOT NULL,
 available_quantity INT NOT NULL
);

CREATE TABLE IF NOT EXISTS members (
 member_id INT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(100) NOT NULL,
 email VARCHAR(120) NOT NULL UNIQUE,
 phone VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS issues (
 issue_id INT AUTO_INCREMENT PRIMARY KEY,
 book_id INT NOT NULL,
 member_id INT NOT NULL,
 issue_date DATE NOT NULL,
 due_date DATE NOT NULL,
 return_date DATE,
 status ENUM('ISSUED','RETURNED') DEFAULT 'ISSUED',
 fine DECIMAL(10,2) DEFAULT 0,
 FOREIGN KEY (book_id) REFERENCES books(book_id),
 FOREIGN KEY (member_id) REFERENCES members(member_id)
);

INSERT INTO books(title,author,category,quantity,available_quantity)
SELECT 'Clean Code','Robert C. Martin','Programming',3,3
WHERE NOT EXISTS (SELECT 1 FROM books WHERE title='Clean Code');

INSERT INTO books(title,author,category,quantity,available_quantity)
SELECT 'The Alchemist','Paulo Coelho','Fiction',2,2
WHERE NOT EXISTS (SELECT 1 FROM books WHERE title='The Alchemist');

INSERT INTO members(name,email,phone)
SELECT 'Demo Member','demo@library.com','9999999999'
WHERE NOT EXISTS (SELECT 1 FROM members WHERE email='demo@library.com');
