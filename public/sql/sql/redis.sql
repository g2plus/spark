CREATE DATABASE IF NOT EXISTS redis;
USE redis;
CREATE TABLE province(
	id INT,
	NAME VARCHAR(20)
);
INSERT INTO province VALUES(1,'湖南');
INSERT INTO province VALUES(2,'湖北');
INSERT INTO province VALUES(3,'广东');
INSERT INTO province VALUES(4,'河南');