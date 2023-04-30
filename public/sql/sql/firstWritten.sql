#数据库的操作
CREATE DATABASE IF NOT EXISTS mydb; # 如果数据库mydb1不存在，则创建数据库
USE mydb;
DROP DATABASE dbname; #删除数据库
SHOW DATABASES; -- 查看有几个数据库
SHOW CREATE DATABASE dbname;# 查看数据库的字符集


#创建数据表
CREATE TABLE t_employee(
	empno INT, --
	ename VARCHAR(20), --
	job VARCHAR(40), --
	MGR INT,--
	Hiredate DATE, --
	sal DOUBLE(10,2),-- 
	comm DOUBLE(10,2),--
	deptno INT --

);

#向数据表中添加数据
INSERT INTO t_employee (empno,ename,job,MGR,Hiredate,sal,deptno) VALUES (7369,'SMITH','CLEAR',7902,'1981-03-12',800,20);
INSERT INTO t_employee (empno,ename,job,MGR,Hiredate,sal,comm,deptno) VALUES (7499,'ALLEN','SALESMAN',7698,'1982-03-12',1600,300,30);
INSERT INTO t_employee (empno,ename,job,MGR,Hiredate,sal,comm,deptno) VALUES (7521,'MARD','SALESMAN',7698,'1983-03-12',1250,500,30);
INSERT INTO t_employee (empno,ename,job,MGR,Hiredate,sal,deptno) VALUES (7566,'JONES','MANAGER',7839,'1981-03-12',2975,20);
INSERT INTO t_employee (empno,ename,job,MGR,Hiredate,sal,comm,deptno) VALUES (7654,'MARRTIN','SALESMAN',7698,'1981-03-12',2850,1400,30);
INSERT INTO t_employee (empno,ename,job,MGR,Hiredate,sal,deptno) VALUES (7698,'BLAKE','MANAGER',7839,'1981-03-12',2850,30);
INSERT INTO t_employee (empno,ename,job,MGR,Hiredate,sal,deptno) VALUES (7782,'CLARK','MANAGER',7839,'1985-03-12',2450,20);
INSERT INTO t_employee (empno,ename,job,MGR,Hiredate,sal,deptno) VALUES (7788,'SCOTT','ANALYST',7566,'1981-03-12',3000,10);
INSERT INTO t_employee (empno,ename,job,Hiredate,sal,deptno) VALUES (7839,'KING','PRESIDENT','1981-03-12',5000,10);
INSERT INTO t_employee (empno,ename,job,MGR,Hiredate,sal,comm,deptno) VALUES (7844,'TURNER','SALESMAN',7698,'1989-03-12',1500,0,30);
INSERT INTO t_employee (empno,ename,job,MGR,Hiredate,sal,deptno) VALUES (7876,'ADAMS','CLEAR',7788,'1998-03-12',1100,20);
INSERT INTO t_employee (empno,ename,job,MGR,Hiredate,sal,deptno) VALUES (7900,'JAMES','CLEAR',7698,'1987-03-12',950,30);
INSERT INTO t_employee (empno,ename,job,MGR,Hiredate,sal,deptno) VALUES (7902,'FORD','ANALYST',7566,'0000-00-00',3000,20);
INSERT INTO t_employee (empno,ename,job,MGR,Hire DATE,sal,deptno)VALUES (7934,'MILLER','CLEAR',7782,'1981-03-12',1300,10);

#查询员工的所有信息
SELECT empno,ename,job,MGR,Hiredate,sal,deptno,comm FROM t_employee;


#查询所有员工的姓名和职位
SELECT ename,job FROM t_employee;

#查询所有员工的职位(去掉重复的)
SELECT DISTINCT job FROM t_employee;

#查询所有员工的姓名和总金额(薪资+佣金)
SELECT ename,IFNULL(sal,0)+IFNULL(comm,0) AS total FROM t_employee;

#查询部门编号等于20的所有员工信息
SELECT empno,ename,job,MGR,Hiredate,sal,deptno,comm FROM t_employee WHERE deptno=20;

#查询职位是clear并且薪资大于1000的员工信息
SELECT empno,ename,job,MGR,Hiredate,sal,deptno,comm FROM t_employee WHERE job='clear' AND sal>1000;


#查询薪资在2000到3000之间的所有员工信息
SELECT empno,ename,job,MGR,Hiredate,sal,deptno,comm FROM t_employee WHERE sal BETWEEN 2000 AND 3000;

#查询佣金是null 的所有员工信息
SELECT empno,ename,job,MGR,Hiredate,sal,deptno,comm FROM t_employee WHERE comm IS NULL;

#查询部门编号在10和20中的所有员工信息
SELECT empno,ename,job,MGR,Hiredate,sal,deptno,comm FROM t_employee WHERE deptno IN(10,20);

#查询员工姓名包含M的所有员工信息
SELECT empno,ename,job,MGR,Hiredate, sal,deptno,comm FROM t_employee WHERE ename LIKE '%M%';

#查询所有员工信息按照薪资降序排列
SELECT * FROM t_employee ORDER BY IFNULL(sal,0)+IFNULL(comm,0) DESC;


#查询所有员工信息先按照薪资升序排列在按照雇佣时间降序排列
SELECT empno,ename,job,MGR,Hiredate, sal,deptno,comm FROM t_employee ORDER BY  IFNULL(sal, 0)+IFNULL(comm,0) ASC,Hiredate DESC;

#查询入职最早的员工
SELECT * FROM t_employee WHERE Hiredate = (SELECT MIN(Hiredate) FROM t_employee);

#查找入职员工时间排名倒数前三的员工所有信息SQL
#LIMIT m,n : 表示从第m+1条开始，取n条数据；
#LIMIT 0,n : 表示从第0+1条开始，取n条数据，
#m，开始索引, n数据个数
SELECT * FROM t_employee ORDER BY Hiredate DESC LIMIT 0,3;

#统计所有员工的个数
SELECT COUNT(empno) FROM t_employee;

#查询部门编号等于10的所有员工的平均薪资
SELECT AVG(sal) FROM t_employee WHERE deptno=10;

#查询所有员工中薪资最高的员工信息
SELECT * FROM t_employee WHERE sal=(SELECT MAX(sal) FROM t_employee);

#查询每个部门的最高薪资
SELECT deptno, MAX(sal) AS MAXSAL FROM t_employee GROUP BY deptno;

#查询每个部门的员工个数
SELECT deptno, COUNT(empno)FROM t_employee GROUP BY deptno;

#查询每个部门平均薪资在1000以上的部门编号和平均薪资
SELECT deptno, AVG(sal) AS avgerage FROM t_employee GROUP BY deptno HAVING avgerage>1000 ORDER BY avgerage DESC LIMIT 0,3;