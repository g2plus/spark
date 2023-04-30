DESC emp;
-- 1.查询出至少有一个员工的所有部门
SELECT dept.deptno,dept.`dname`,c.count 
FROM (SELECT deptno,COUNT(empno) AS COUNT FROM emp GROUP BY deptno HAVING COUNT>1) AS c, dept
WHERE c.deptno=dept.deptno;

-- 2.查询出部门名称和这些部门的员工信息，同时查询出没有员工的部门
SELECT dept.`dname`,d.deptno,d.ename,d.empno 
FROM(SELECT dept.dname,c.deptno,c.ename,c.empno FROM (SELECT emp.`empno`,emp.ename,emp.`deptno` FROM emp) AS c,dept WHERE c.deptno=dept.`deptno`) AS d
RIGHT JOIN dept ON d.dname=dept.`dname`;

-- 3.查询所有“CLERK"(办事员) 的姓名和部门名称，以及部门人数
SELECT b.ename,b.deptno,b.dname, c.count 
	FROM (SELECT a.ename,a.deptno,dept.`dname` FROM (SELECT emp.ename,emp.deptno FROM emp WHERE job='CLERK') AS a,dept WHERE a.deptno=dept.`deptno`) AS b, 
	(SELECT deptno,COUNT(empno)AS COUNT FROM emp GROUP BY deptno) AS c 
	WHERE b.deptno=c.deptno; 
	
-- 4.查询出所有员工的姓名和直接上级的姓名
SELECT  
a.ename AS 员工姓名,b.ename AS 直接上级姓名
FROM emp AS a
LEFT JOIN emp AS b
ON a.mgr=b.empno;
-- 5.查询各job的员工工资的最大值，最小值，平均值，总和
SELECT job,MAX(sal),MIN(sal),AVG(sal),SUM(sal) FROM emp GROUP BY job;

-- 6.选择统计各个job的员工人数(提示:对job进行分组)
SELECT job AS 工作岗位, COUNT(empno)AS 员工数 FROM emp GROUP BY job;

-- 7.查询员工最高工资和最低工资的差距,列名为DIFFERENCE
SELECT MAX(sal) AS MAX,MIN(sal) AS MIN,MAX(sal)-MIN(sal)AS DIFERENCE FROM emp;

-- 8.查询各个管理者属下员工的最低工资，其中最低工资不能低于800，没有管理者的员工不计算在内
SELECT c.employee_name AS 员工,c.employer_name AS 管理者,c.sal AS 工资 FROM (SELECT a.ename AS employee_name, b.ename AS employer_name,a.sal FROM emp AS a INNER JOIN emp AS b ON a.mgr=b.empno) AS c WHERE c.sal>800;

-- 9.查询所有部门的部门名字dname，所在位置loc，员工数量和工资平均值
SELECT b.dname AS 部门名字,b.loc AS 所在位置,COUNT(empno) AS 员工数量,AVG(sal) AS 工资平均值 FROM emp AS a RIGHT JOIN dept AS b ON a.deptno=b.deptno GROUP BY b.deptno;

-- 10.查询和scott相同部门的员工姓名ename和雇用日期hiredate
SELECT deptno FROM emp WHERE emp.ename='scott'; --查询scott所在的部门
SELECT a.ename, a.deptno,a.`hiredate` FROM emp AS a,(SELECT deptno FROM emp WHERE emp.`ename`='scott') AS b WHERE a.deptno=b.deptno;

-- 11.查询工资比公司平均工资高的所有员工的员工号empno，姓名ename和工资sal
SELECT AVG(sal) average FROM emp; -- 平均工资
SELECT empno, ename, sal FROM emp WHERE sal>(SELECT AVG(sal) FROM emp);

-- 12.查询和姓名中包含字母M的员工在相同部门的员工的员工号empno和姓名ename
SELECT empno, ename,deptno FROM emp WHERE ename LIKE '%M%' ORDER BY deptno ASC;`dept``dept`

-- 13.查询在部门的loc为newYork的部门工作的员工的员工姓名ename，部门名称dname和岗位名称job
SELECT a.ename, b.dname, a.job, b.loc FROM emp AS a, dept AS b WHERE a.`deptno`=b.`deptno` AND b.`loc`='NEW YORK';

-- 14.查询管理者是king的员?ば彰鹐name和工资sal
SELECT 
c.employee, c.sal
FROM 
(SELECT 
a.ename AS employee, a.sal, b.ename AS mgrname 
FROM emp AS a, emp AS b 
WHERE a.mgr=b.empno) AS c WHERE c.mgrname='KING';

-- 15.显示sales部门有哪些职位
SELECT c.dname,c.job FROM (SELECT dept.`deptno`,dept.`dname`,emp.job FROM dept, emp) AS c WHERE c.dname='SALES' GROUP BY c.job;

-- 16.各个部门中工资大于1500的员工人数
SELECT c.dname, COUNT(deptno) AS COUNT FROM (SELECT dept.`dname`,c.* FROM (SELECT sal,deptno FROM emp WHERE sal>1500) AS c JOIN dept WHERE dept.deptno=c.deptno) AS c GROUP BY c.deptno;

-- 17.哪些员工的工资，高于整个公司的平均工资，列出员工的名字和工资（降序）
SELECT emp.ename AS NAME,emp.sal AS salary FROM emp WHERE sal>(SELECT AVG(sal) FROM  emp) ORDER BY sal DESC;

-- 18.所在部门平均工资高于1500的员工名字
SELECT c.name,c.deptno,c.average, dept.`dname` 
FROM (SELECT b.`ename` AS NAME,b.deptno AS deptno,a.average AS average 
FROM (SELECT deptno,AVG(sal)AS average FROM emp GROUP BY deptno HAVING average>1500) AS a, emp AS b WHERE a.deptno=b.deptno) AS c, dept 
WHERE c.deptno=dept.`deptno`;

-- 19.列出各个部门中工资最高的员工的信息：名字、部门号、工资
SELECT c.*, dept.`dname` FROM (SELECT emp.ename,emp.deptno,MAX(sal) AS MAX FROM emp GROUP BY deptno) AS c, dept WHERE c.deptno=dept.`deptno`;

-- 20.哪个部门的平均工资是最高的，列出部门号、平均工资
SELECT d.max,d.deptno,dept.`dname` FROM (SELECT MAX(average) AS MAX,c.deptno  FROM (SELECT deptno,AVG(sal) AS average FROM emp GROUP BY deptno) AS c) AS d, dept WHERE d.deptno=dept.`deptno`;



# 参考链接 https://blog.csdn.net/qq_34870595/article/details/52914536
-- 21.每个部门每个职位的平均工资和平均奖金，平均奖金大于300显示奖金不错，100到300奖金一般，小于100基本没有奖金
SELECT deptno,job,AVG(sal) AS avgsal,
       AVG(IFNULL(comm,0)) avgcomm,
CASE 
WHEN AVG(IFNULL(comm,0))>300 THEN '奖金不错'
WHEN AVG(IFNULL(comm,0))>=100 AND AVG(IFNULL(comm,0))<300 THEN '奖金一般'
WHEN AVG(IFNULL(comm,0))<100 THEN '基本没有奖金'
END 奖金状况
FROM emp
GROUP BY job
ORDER BY avgsal DESC;


-- 22.查询员工的姓名和工资水平，工资3000以内显示极度贫困，工资在3000到4999之间属于贫困，大于4999则正常1
SELECT ename AS 员工姓名,sal AS 工资,
CASE 
WHEN sal<3000 THEN '极度贫困'
WHEN sal>=3000 AND sal<=4999 THEN '贫困'
WHEN sal>4999 THEN '正常'
END 工资水平
FROM emp