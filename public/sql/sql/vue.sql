CREATE DATABASE IF NOT EXISTS vue;
USE vue;
CREATE TABLE USER(
	id INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(20),
	PASSWORD VARCHAR(20)
);
INSERT INTO USER VALUES(1,"root","root");
CREATE TABLE student(
	number VARCHAR(20),
	NAME VARCHAR(20),
	birthday DATE,
	address VARCHAR(30)
);
INSERT INTO student VALUES('hm001','张一','1997-6-6','长沙');
INSERT INTO student VALUES('hm002','张二','1997-06-06','长沙');
INSERT INTO student VALUES('hm003','张三','1997-06-06','长沙');
INSERT INTO student VALUES('hm004','张四','1997-06-06','长沙');
INSERT INTO student VALUES('hm005','李四','1997-06-06','长沙');
INSERT INTO student VALUES('hm006','王五','1997-06-06','长沙');
INSERT INTO student VALUES('hm007','王一','1997-06-06','长沙');
INSERT INTO student VALUES('hm008','王二','1997-06-06','长沙');
INSERT INTO student VALUES('hm009','王三','1997-06-06','长沙');
INSERT INTO student VALUES('hm010','王四','1997-06-06','长沙');