
CREATE DATABASE IF NOT EXISTS db2;
USE db2;
DROP TABLE student;
CREATE TABLE student(
	SNO INT,
	Sname VARCHAR(20),
	Sage INT,
	Ssex VARCHAR(20)
);
INSERT INTO student VALUES(160101,'陈柏霖',23,'0');
INSERT INTO student VALUES(160102,'陈冠希',24,'1');
INSERT INTO student VALUES(160103,'陈浩民',24,'0');
INSERT INTO student VALUES(160104,'陈启泰',24,'1');
INSERT INTO student VALUES(160105,'陈司翰',23,'1');
INSERT INTO student VALUES(160106,'陈小春',24,'1');
INSERT INTO student VALUES(160107,'陈晓东',22,'0');
INSERT INTO student VALUES(160108,'陈勋奇',23,'0');
INSERT INTO student VALUES(160109,'马冬梅',23,'0');
INSERT INTO student VALUES(160110,'何佳铭',21,'0');


DROP TABLE course;
CREATE TABLE course(
	CNO INT,
	Cname VARCHAR(20),
	TNO INT
);
INSERT INTO course VALUES(1,'企业管理',1);
INSERT INTO course VALUES(5,'高等数学',1);
INSERT INTO course VALUES(6,'大学英语',1);
INSERT INTO course VALUES(2,'马克思',2);
INSERT INTO course VALUES(3,'UML',3);
INSERT INTO course VALUES(4,'数据库',4);


DROP TABLE SC;
CREATE TABLE SC(
	SNO INT,
	CNO INT,
	score INT
);
INSERT INTO SC VALUES(160101,1,67);
INSERT INTO SC VALUES(160102,1,73);
INSERT INTO SC VALUES(160103,1,71);
INSERT INTO SC VALUES(160104,1,61);
INSERT INTO SC VALUES(160105,1,52);
INSERT INTO SC VALUES(160106,1,68);
INSERT INTO SC VALUES(160107,1,62);
INSERT INTO SC VALUES(160108,1,72);
INSERT INTO SC VALUES(160101,2,77);
INSERT INTO SC VALUES(160102,2,74);
INSERT INTO SC VALUES(160103,2,99);
INSERT INTO SC VALUES(160104,2,97);
INSERT INTO SC VALUES(160105,2,76);
INSERT INTO SC VALUES(160106,2,97);
INSERT INTO SC VALUES(160107,2,50);
INSERT INTO SC VALUES(160108,2,79);
INSERT INTO SC VALUES(160101,3,97);
INSERT INTO SC VALUES(160102,3,100);
INSERT INTO SC VALUES(160103,3,62);
INSERT INTO SC VALUES(160104,3,90);
INSERT INTO SC VALUES(160105,3,67);
INSERT INTO SC VALUES(160106,3,95);
INSERT INTO SC VALUES(160107,3,84);
INSERT INTO SC VALUES(160108,3,56);
INSERT INTO SC VALUES(160101,4,71);
INSERT INTO SC VALUES(160102,4,58);
INSERT INTO SC VALUES(160103,4,70);
INSERT INTO SC VALUES(160104,4,54);
INSERT INTO SC VALUES(160105,4,75);
INSERT INTO SC VALUES(160106,4,100);
INSERT INTO SC VALUES(160107,4,65);
INSERT INTO SC VALUES(160108,4,98);
INSERT INTO SC VALUES(160109,2,98);
INSERT INTO SC VALUES(160109,3,79);
INSERT INTO SC VALUES(160109,3,95);
INSERT INTO SC VALUES(160110,1,98);
INSERT INTO SC VALUES(160110,5,98);
INSERT INTO SC VALUES(160110,6,98);

DROP TABLE Teacher;
CREATE TABLE Teacher(
	TNO INT,
	Tname VARCHAR(20)
);
INSERT INTO Teacher VALUES(1,'龚鑫');
INSERT INTO Teacher VALUES(2,'唐爽');
INSERT INTO Teacher VALUES(3,'刘彪');
INSERT INTO Teacher VALUES(4,'黄江华');

#问题：
#1、查询“1”课程比“2”课程成绩高的所有学生的学号
SELECT sno,cno,score FROM sc WHERE sc.cno=1 -- 获取学习课程1的学生名单
SELECT sno,cno,score FROM sc WHERE sc.cno=2 -- 获取学习课程2的学生名单
SELECT tablea.sno,tablea.score1,tableb.score2 FROM (SELECT sno,cno AS con1,score AS score1 FROM sc WHERE sc.cno=1 ) AS tablea,(SELECT sno,cno AS con2,score AS score2 FROM sc WHERE sc.cno=2) AS tableb WHERE tablea.sno=tableb.sno; -- 获取学习课程1，2的学生名单
SELECT c.sno FROM (SELECT tablea.sno,tablea.score1,tableb.score2 FROM (SELECT sno,cno AS con1,score AS score1 FROM sc WHERE sc.cno=1 ) AS tablea,(SELECT sno,cno AS con2,score AS score2 FROM sc WHERE sc.cno=2) AS tableb WHERE tablea.sno=tableb.sno) AS c WHERE c.score1>c.score2; -- 获取课程1分数比课程高的学生学号
#2、查询平均成绩大于60分的同学的学号和平均成绩
SELECT sno, AVG(score) AS avgscore FROM sc GROUP BY sno HAVING avgscore>60;
#3、查询所有同学的学号、姓名、选课数、总成绩
SELECT sno,SUM(cno) AS 选课数, SUM(score) AS 总成绩 FROM sc GROUP BY sno; -- 查询某个学号对相应学生的选课数，总成绩
SELECT tableb.sno,student.Sname,tableb.选课数,tableb.总成绩 FROM student, (SELECT sno,SUM(cno) AS 选课数, SUM(score) AS 总成绩 FROM sc GROUP BY sno) AS tableb WHERE student.sno=tableb.sno;
#4、查询姓“龚”的老师的个数
SELECT COUNT(Tname) AS 姓“龚”的老师的个数 FROM teacher WHERE teacher.Tname LIKE '龚%';
SELECT * FROM teacher;
#5、查询没学过“龚鑫”老师课的同学的学号、姓名
SELECT TNO FROM teacher WHERE teacher.Tname='龚鑫';
SELECT b.CNO FROM (SELECT TNO FROM teacher WHERE teacher.Tname='龚鑫') AS a,(SELECT TNO,CNO FROM course) AS b WHERE a.TNO=b.TNO; -- 查询龚鑫老师的课程号
SELECT SNO,CNO FROM sc GROUP BY SNO -- 根据学号分组，查看学生的选课表
SELECT tablea.SNO FROM (SELECT SNO,CNO FROM sc GROUP BY SNO) AS tablea WHERE tablea.CNO !=1;
SELECT student.SNO,student.Sname FROM student,(SELECT tablea.SNO FROM (SELECT SNO,CNO FROM sc GROUP BY SNO) AS tablea WHERE tablea.CNO !=1) AS newtable WHERE newtable.SNO=student.SNO;
#6、查询学过“1”并且也学过编号“2”课程的同学的学号、姓名
SELECT * FROM sc WHERE sc.cno=1; -- 查询学过“1” 的学生学号
SELECT * FROM sc WHERE sc.cno=2; -- 查询学过“2” 的学生学号
SELECT b.SNO FROM (SELECT * FROM sc WHERE sc.cno=1)AS a,(SELECT * FROM sc WHERE sc.cno=2) AS b WHERE a.SNO=b.SNO; -- 查询学过“1”并且也学过编号“2”课程的同学的学号
SELECT student.SNO,student.Sname FROM student,(SELECT b.SNO FROM (SELECT * FROM sc WHERE sc.cno=1)AS a,(SELECT * FROM sc WHERE sc.cno=2) AS b WHERE a.SNO=b.SNO) AS tablea WHERE student.SNO=tablea.SNO;

#参考链接 https://blog.csdn.net/weixin_43761679/article/details/112980469
#7、查询学过“龚鑫”老师所教的所有课的同学的学号、姓名
SELECT SNO,Sname FROM student s WHERE NOT EXISTS 
(SELECT * FROM course WHERE course.TNO=
(SELECT TNO FROM teacher WHERE teacher.Tname='龚鑫') 
AND course.CNO NOT IN 
(SELECT course.CNO FROM course,sc WHERE sc.CNO=course.CNO AND  sc.SNO=s.SNO))
#8、查询课程编号“2”的成绩比课程编号“1”课程低的所有同学的学号、姓名
SELECT SNO FROM SC WHERE SC.CNO=2;-- 查询x学过课程编号“2”的学生学号
SELECT SNO FROM SC WHERE SC.CNO=1;-- 查询课程编号“1”的学生学号
SELECT A.SNO,A.score2,B.score1 FROM (SELECT SNO, score AS score2 FROM SC WHERE SC.CNO=2) AS A, (SELECT SNO,score AS score1 FROM SC WHERE SC.CNO=1) AS B WHERE A.SNO=B.SNO; -- 查询学过课程2与课程1的学生信息
SELECT c.SNO FROM (SELECT A.SNO,A.score2,B.score1 FROM (SELECT SNO, score AS score2 FROM SC WHERE SC.CNO=2) AS A, 
(SELECT SNO,score AS score1 FROM SC WHERE SC.CNO=1) AS B WHERE A.SNO=B.SNO) AS c WHERE c.score2<c.score1; -- 查询学过课程2分数与课程1分数低的学生信息

SELECT student.SNO,student.Sname FROM student,
(SELECT c.SNO FROM (SELECT A.SNO,A.score2,B.score1 FROM (SELECT SNO, score AS score2 FROM SC WHERE SC.CNO=2) AS A, 
(SELECT SNO,score AS score1 FROM SC WHERE SC.CNO=1) AS B WHERE A.SNO=B.SNO) AS c WHERE c.score2<c.score1
)AS d WHERE student.SNO=d.SNO;
#9、查询所有课程成绩小于60分的同学的学号、姓名

#10、查询没有学全所有课的同学的学号、姓名

#11、查询至少有一门课与学号为“160101”的同学所学相同的同学的学号和姓名

#12、查询至少学过学号为“160101”同学所有一门课的其他同学学号和姓名

#13、把“SC”表中“龚鑫”老师教的课的成绩都更改为此课程的平均成绩

#14、查询和“160202”号的同学学习的课程完全相同的其他同学学号和姓名

#15、删除学习“龚鑫”老师课的SC表记录

#16、向SC表中插入一些记录，这些记录要求符合以下条件：没有上过编号“3”课程的同学学号当上过编号”2“课程的平均成绩

#17、按平均成绩从高到低显示所有学生的“数据库”、“企业管理”、“uml”三门的课程成绩，按如下形式显示： 学生ID,,数据库,企业管理,uml有效课程数,有效平均分

#18、查询各科成绩最高和最低的分：以如下形式显示：课程ID，最高分，最低分

#19、按各科平均成绩从低到高和及格率的百分数从高到低顺序

#20、查询如下课程平均成绩和及格率的百分数(用"1行"显示): 企业管理（001），马克思（002），UML （003），数据库（004）

#21、查询不同老师所教不同课程平均分从高到低显示

#22、查询如下课程成绩第 3 名到第 6 名的学生成绩单：企业管理（001），马克思（002），UML （003），数据库（004）[学生ID],[学生姓名],企业管理,马克思,UML,数据库,平均成绩

#23、统计列印各科成绩,各分数段人数:课程ID,课程名称,[100-85],[85-70],[70-60],[ <60]

#24、查询学生平均成绩及其名次

#25、查询各科成绩前三名的记录:(不考虑成绩并列情况)

#26、查询每门课程被选修的学生数

#27、查询出只选修了一门课程的全部学生的学号和姓名

#28、查询男生、女生人数

#29、查询姓“陈”的学生名单

#30、查询同名同性学生名单，并统计同名人数

#31、年龄为22岁的学生名单

#32、查询每门课程的平均成绩，结果按平均成绩升序排列，平均成绩相同时，按课程号降序排列

#33、查询平均成绩大于85的所有学生的学号、姓名和平均成绩

#34、查询课程名称为“数据库”，且分数低于60的学生姓名和分数

#35、查询所有学生的选课情况

#36、查询任何一门课程成绩在70分以上的姓名、课程名称和分数

#37、查询不及格的课程，并按课程号从大到小排列

#38、查询课程编号为3且课程成绩在80分以上的学生的学号和姓名

#39、求选了课程的学生人数

#40、查询选修“龚鑫”老师所授课程的学生中，成绩最高的学生姓名及其成绩

#41、查询各个课程及相应的选修人数

#42、查询不同课程成绩相同的学生的学号、课程号、学生成绩

#43、查询每门功成绩最好的前两名

#44、统计每门课程的学生选修人数（超过10人的课程才统计）。要求输出课程号和选修人数，查询结果按人数降序排列，查询结果按人数降序排列，若人数相同，按课程号升序排列

#45、检索至少选修两门课程的学生学号

#46、查询全部学生都选修的课程的课程号和课程名

#47、查询没学过“龚鑫”老师讲授的任一门课程的学生姓名

#48、查询两门以上不及格课程的同学的学号及其平均成绩

#49、检索“4”课程分数小于60，按分数降序排列的同学学号

#50、删除“1602022”同学的“1”课程的成绩