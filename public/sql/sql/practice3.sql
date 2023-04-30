CREATE DATABASE IF NOT EXISTS db;
USE db;

CREATE TABLE class(
	classId INT PRIMARY KEY,
	className VARCHAR(50)
);

INSERT INTO class VALUES(1601,'16电子信息工程一班');
INSERT INTO class VALUES(1602,'16电子信息工程二班');
INSERT INTO class VALUES(1701,'17电子信息工程一班');


CREATE TABLE student(
	stuNum INT PRIMARY KEY,
	stuName VARCHAR(50),
	cid INT,
	CONSTRAINT sc_fk1 FOREIGN KEY (cid) REFERENCES class(classId)
);

INSERT INTO Student VALUES (160101,'陈柏霖',1601);	
INSERT INTO Student VALUES (160102,'陈冠希',1601);	
INSERT INTO Student VALUES (160103,'陈浩民',1601);	
INSERT INTO Student VALUES (160104,'陈启泰',1601);	
INSERT INTO Student VALUES (160105,'陈司翰',1601);	
INSERT INTO Student VALUES (160106,'陈小春',1601);	
INSERT INTO Student VALUES (160107,'陈晓东',1601);	
INSERT INTO Student VALUES (160108,'陈勋奇',1601);	
INSERT INTO Student VALUES (160109,'陈奕迅',1601);	
INSERT INTO Student VALUES (160110,'陈志朋',1601);	
INSERT INTO Student VALUES (160111,'丁子峻',1601);	
INSERT INTO Student VALUES (160112,'窦智孔',1601);	
INSERT INTO Student VALUES (160113,'杜德伟',1601);	
INSERT INTO Student VALUES (160114,'杜汶泽',1601);	
INSERT INTO Student VALUES (160115,'范逸臣',1601);	

INSERT INTO Student VALUES (160201,'郭晋安',1602);	
INSERT INTO Student VALUES (160202,'郭品超',1602);	
INSERT INTO Student VALUES (160203,'何润东',1602);	
INSERT INTO Student VALUES (160204,'黄家强',1602);	
INSERT INTO Student VALUES (160205,'黄立行',1602);	
INSERT INTO Student VALUES (160206,'黄品源',1602);	
INSERT INTO Student VALUES (160207,'黄秋生',1602);	
INSERT INTO Student VALUES (160208,'黄日华',1602);	
INSERT INTO Student VALUES (160209,'黄仲昆',1602);	
INSERT INTO Student VALUES (160210,'霍建华',1602);	
INSERT INTO Student VALUES (160211,'姜育恒',1602);	
INSERT INTO Student VALUES (160212,'蒋志光',1602);	
INSERT INTO Student VALUES (160213,'焦恩俊',1602);	
INSERT INTO Student VALUES (160214,'金城武',1602);	
INSERT INTO Student VALUES (160215,'柯受良',1602);	

INSERT INTO Student VALUES (170101,'古巨基',1701);	
INSERT INTO Student VALUES (170102,'古天乐',1701);	
INSERT INTO Student VALUES (170103,'郭富城',1701);	
INSERT INTO Student VALUES (170104,'蓝正龙',1701);	
INSERT INTO Student VALUES (170105,'李克勤',1701);	
INSERT INTO Student VALUES (170106,'李小龙',1701);	
INSERT INTO Student VALUES (170107,'李子雄',1701);	
INSERT INTO Student VALUES (170108,'梁朝伟',1701);	
INSERT INTO Student VALUES (170109,'梁汉文',1701);	
INSERT INTO Student VALUES (170110,'梁家辉',1701);	
INSERT INTO Student VALUES (170111,'林志炫',1701);	
INSERT INTO Student VALUES (170112,'林志颖',1701);	
INSERT INTO Student VALUES (170113,'刘德华',1701);	
INSERT INTO Student VALUES (170114,'刘畊宏',1701);	
INSERT INTO Student VALUES (170115,'吕良伟',1701);	

-- drop table Scores;
CREATE TABLE scores(
	scoreId INT PRIMARY KEY,
	kcbh VARCHAR(90),
	kcmc VARCHAR(30)`scores`
);


INSERT INTO Scores VALUES(1,'math','高等数学');
INSERT INTO Scores VALUES(2,'College english','大学英语');
INSERT INTO Scores VALUES(3,'Electronics Technology','模拟电子技术');
INSERT INTO Scores VALUES(4,'Digital Electronics Technology;','数字电子技术');
INSERT INTO Scores VALUES(5,'Principle of Communication','通信原理');
INSERT INTO Scores VALUES(6,'Principles of Automatic Contro','自动控制原理');
INSERT INTO Scores VALUES(7,'Microcontroller Theory and Applications','单片机原理与应用');

-- DROP TABLE student_score;
CREATE TABLE student_score(
	pid INT PRIMARY KEY AUTO_INCREMENT,
	score INT,
	sNum INT,
	scId INT,
	CONSTRAINT FK2 FOREIGN KEY (sNum) REFERENCES Student(stuNum),
	CONSTRAINT FK3 FOREIGN KEY (scId) REFERENCES Scores(scoreId) 
);
INSERT INTO student_score VALUES(NULL,61,160101,1);
INSERT INTO student_score VALUES(NULL,78,160101,4);
INSERT INTO student_score VALUES(NULL,65,160101,5);
INSERT INTO student_score VALUES(NULL,90,160101,6);
INSERT INTO student_score VALUES(NULL,72,160101,7);
INSERT INTO student_score VALUES(NULL,64,160102,1);
INSERT INTO student_score VALUES(NULL,52,160102,2);
INSERT INTO student_score VALUES(NULL,56,160102,3);
INSERT INTO student_score VALUES(NULL,64,160102,4);
INSERT INTO student_score VALUES(NULL,70,160102,5);
INSERT INTO student_score VALUES(NULL,68,160102,6);
INSERT INTO student_score VALUES(NULL,75,160102,7);
INSERT INTO student_score VALUES(NULL,72,160103,1);
INSERT INTO student_score VALUES(NULL,51,160103,2);
INSERT INTO student_score VALUES(NULL,60,160103,3);
INSERT INTO student_score VALUES(NULL,59,160103,4);
INSERT INTO student_score VALUES(NULL,85,160103,5);
INSERT INTO student_score VALUES(NULL,91,160103,6);
INSERT INTO student_score VALUES(NULL,81,160103,7);
INSERT INTO student_score VALUES(NULL,56,160104,1);
INSERT INTO student_score VALUES(NULL,78,160104,2);
INSERT INTO student_score VALUES(NULL,91,160104,3);
INSERT INTO student_score VALUES(NULL,69,160104,4);
INSERT INTO student_score VALUES(NULL,88,160104,5);
INSERT INTO student_score VALUES(NULL,84,160104,6);
INSERT INTO student_score VALUES(NULL,88,160104,7);
INSERT INTO student_score VALUES(NULL,73,160105,1);
INSERT INTO student_score VALUES(NULL,87,160105,2);
INSERT INTO student_score VALUES(NULL,97,160105,3);
INSERT INTO student_score VALUES(NULL,66,160105,4);
INSERT INTO student_score VALUES(NULL,55,160105,5);
INSERT INTO student_score VALUES(NULL,93,160105,6);
INSERT INTO student_score VALUES(NULL,61,160105,7);
INSERT INTO student_score VALUES(NULL,81,160201,1);
INSERT INTO student_score VALUES(NULL,90,160201,2);
INSERT INTO student_score VALUES(NULL,81,160201,3);
INSERT INTO student_score VALUES(NULL,59,160201,4);
INSERT INTO student_score VALUES(NULL,75,160201,5);
INSERT INTO student_score VALUES(NULL,74,160201,6);
INSERT INTO student_score VALUES(NULL,80,160201,7);
INSERT INTO student_score VALUES(NULL,67,160202,1);
INSERT INTO student_score VALUES(NULL,75,160202,2);
INSERT INTO student_score VALUES(NULL,66,160202,3);
INSERT INTO student_score VALUES(NULL,95,160202,4);
INSERT INTO student_score VALUES(NULL,86,160202,5);
INSERT INTO student_score VALUES(NULL,69,160202,6);
INSERT INTO student_score VALUES(NULL,90,160202,7);
INSERT INTO student_score VALUES(NULL,56,160203,1);
INSERT INTO student_score VALUES(NULL,56,160203,2);
INSERT INTO student_score VALUES(NULL,56,160203,3);
INSERT INTO student_score VALUES(NULL,85,160203,4);
INSERT INTO student_score VALUES(NULL,53,160203,5);
INSERT INTO student_score VALUES(NULL,82,160203,6);
INSERT INTO student_score VALUES(NULL,87,160203,7);
INSERT INTO student_score VALUES(NULL,87,170103,1);
INSERT INTO student_score VALUES(NULL,70,170103,2);
INSERT INTO student_score VALUES(NULL,94,170103,3);
INSERT INTO student_score VALUES(NULL,52,170103,4);
INSERT INTO student_score VALUES(NULL,76,170103,5);
INSERT INTO student_score VALUES(NULL,84,170103,6);
INSERT INTO student_score VALUES(NULL,50,170103,7);


#请编写sql查询所有学生的学号，姓名及所在的班级，修读课程门数（学号，学生姓名，班级名称，修读课程门数）
SELECT s.`stuNum`AS stuNum,s.`stuName` AS stuName,c.`className`AS className FROM class c, student s WHERE s.cid=c.classId; -- 得到学生的姓名，学号及所在班级的数据表
SELECT sNum AS stuNum,COUNT(scId) AS COUNT FROM student_score GROUP BY sNum; -- 得到某个学生的修读课程门数的数据表
SELECT b.stuNum, b.stuName,b.className,a.COUNT FROM (SELECT sNum AS stuNum,COUNT(scId) AS COUNT FROM student_score GROUP BY sNum) AS a,(SELECT s.`stuNum`AS stuNum,s.`stuName` AS stuName,c.`className`AS className FROM class c, student s WHERE s.cid=c.classId)AS b WHERE a.stuNum=b.stuNum;

#请编写sql查询学生的平均成绩，所有课程成绩和，并按学生所修课程门数最多排序（班级名称，学号，姓名，平均成绩，成绩和）
SELECT s.`stuNum`AS stuNum,s.`stuName` AS stuName,c.`className`AS className FROM class c, student s WHERE s.cid=c.classId;
SELECT sNum AS stuNum,COUNT(scId) AS COUNT, SUM(score)AS SUM, AVG(score)AS AVG FROM student_score GROUP BY sNum ORDER BY COUNT(scId) DESC;
SELECT a.className,a.stuNum,a.stuName,b.AVG,b.SUM, b.COUNT FROM (SELECT s.`stuNum`AS stuNum,s.`stuName` AS stuName,c.`className`AS className FROM class c, student s WHERE s.cid=c.classId) AS a, (SELECT sNum AS stuNum,COUNT(scId) AS COUNT, SUM(score)AS SUM, AVG(score) AVG FROM student_score GROUP BY sNum) AS b WHERE a.stuNum=b.stuNum ORDER BY b.COUNT DESC;

#请编写sql查询每个班级的最好成绩的学生姓名（班级名称，学生姓名）
SELECT s.`stuNum`AS stuNum,s.`stuName` AS stuName,c.`className`AS className FROM class c, student s WHERE s.cid=c.classId; -- 得到学生的姓名，学号及所在班级的数据表
SELECT c.className, c.stuNum, c.stuName,MAX(SUM) FROM (SELECT a.className,a.stuNum,a.stuName,b.AVG,b.SUM, b.COUNT FROM (SELECT s.`stuNum`AS stuNum,s.`stuName` AS stuName,c.`className`AS className FROM class c, student s WHERE s.cid=c.classId) AS a, (SELECT sNum AS stuNum,COUNT(scId) AS COUNT, SUM(score)AS SUM, AVG(score) AVG FROM student_score GROUP BY sNum) AS b WHERE a.stuNum=b.stuNum ORDER BY b.COUNT DESC) AS c GROUP BY className ORDER BY MAX(SUM) ASC;