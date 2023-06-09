`user`CREATE DATABASE IF NOT EXISTS ajax;
USE ajax;
CREATE TABLE USER(
	id INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(20),
	age INT,
	search_total INT	
);
INSERT INTO USER VALUES(NULL,'张三',23,100);
INSERT INTO USER VALUES(NULL,'张三的传奇一生',23,100);
INSERT INTO USER VALUES(NULL,'法外狂徒张三',23,1000);
INSERT INTO USER VALUES(NULL,'张晓东',25,10);
INSERT INTO USER VALUES(NULL,'张艺谋',29,1000000);
INSERT INTO USER VALUES(NULL,'张永辉',20,1);

SELECT NAME,age FROM USER WHERE NAME LIKE '%张%' AND search_total>10;

-- 创建db11数据库
CREATE DATABASE IF NOT EXISTS ajax;

-- 使用db11数据库
USE ajax;

-- 创建数据表
CREATE TABLE news(
	id INT PRIMARY KEY AUTO_INCREMENT,	-- 主键id
	title VARCHAR(999)                      -- 新闻标题
);

-- 插入数据
DELIMITER $
CREATE PROCEDURE create_data()		
BEGIN
DECLARE i INT;		
SET i=1;
WHILE i<=100 DO	
	INSERT INTO news VALUES (NULL,CONCAT('奥巴马罕见介入美国2020大选，警告民主党参选人须“基于现实”',i));	
SET i=i+1;		
END WHILE;
END $

-- 调用存储过程
CALL create_data();