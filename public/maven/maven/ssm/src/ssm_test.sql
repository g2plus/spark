CREATE DATABASE IF NOT EXISTS db;
USE db;
CREATE TABLE student(
	id INT PRIMARY KEY NOT NULL,
	NAME VARCHAR(50),
	age INT
);
INSERT INTO student VALUES(1,'张三',23);
INSERT INTO student VALUES(2,'李四',24);
INSERT INTO student VALUES(3,'王五',25);