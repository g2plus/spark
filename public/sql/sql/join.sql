#1.查询用户的编号、姓名、年龄。订单编号；
SELECT u.id,u.age,u.age,o.number FROM USER  u JOIN orderlist o ON u.id=o.uid;

#2.查询所有的用户。用户的编号、姓名、年龄。订单编号
SELECT u.id,u.name,u.age,o.number FROM USER u LEFT JOIN orderlist o ON u.id=o.uid;

#3.查询所有的订单。用户的编号、姓名、年龄。订单编号
SELECT u.id,u.name,u.age,o.number FROM USER u RIGHT JOIN orderlist o ON u.id=o.uid;


#4.查询用户年龄大于23岁的信息。显示用户的编号、姓名、年龄。订单编号
SELECT temp.id,temp.name,temp.age,o.number FROM (SELECT * FROM USER WHERE age>23) AS temp, orderlist o WHERE temp.id=o.uid;

SELECT u.id, u.name, u.age,o.number FROM USER u, orderlist o WHERE u.id=o.uid AND u.age>23;

#5.查询张三和李四用户的信息。显示用户的编号、姓名、年龄。订单编号
SELECT temp.id,temp.name,temp.age,o.number  FROM (SELECT * FROM USER WHERE NAME IN('张三','李四')) AS temp,orderlist o WHERE temp.id=o.uid;

SELECT temp.id,temp.name,temp.age,o.number FROM USER temp, orderlist o WHERE temp.id=o.uid AND temp.name IN('张三','李四');

#6.查询商品分类的编号、分类名称。分类下的商品名称
SELECT c.id, c.NAME AS category, P.NAME AS product FROM category c,product p WHERE c.id=p.cid;

#7.查询所有的商品分类。商品分类的编号、分类名称。分类下的商品名称
SELECT c.id, c.NAME AS category, P.NAME FROM category c LEFT JOIN product p ON c.id=p.cid; 

#8.查询所有的商品信息。商品分类的编号、分类名称。分类下的商品名称
SELECT c.id, c.NAME AS category, P.NAME FROM category c RIGHT JOIN product p ON c.id=p.cid; 

#9.查询所有的用户和所有的商品。显示用户的编号、姓名、年龄。商品名称
SELECT u.id,u.name, u.age, p.NAME FROM USER u,product p,us_pro WHERE us_pro.pid=p.id AND us_pro.uid=u.id;


#10.查询张三和李四这两个用户可以看到的商品。显示用户的编号、姓名、年龄。商品名称
SELECT u.id,u.name, u.age, p.NAME FROM USER u,product p,us_pro WHERE us_pro.pid=p.id AND us_pro.uid=u.id AND u.name IN('张三','李四');
SELECT u.id,u.name, u.age, p.NAME FROM (SELECT * FROM USER WHERE NAME IN('张三','李四')) u, product p, us_pro WHERE us_pro.pid=p.id AND us_pro.uid=u.id;

/*
select 
	u.id,
	u.name,1
	u.age,
	o.number
from USER u
INNER join	
	orderlist o
ON 
	u.id=o.uid;
	
SELECT 
	u.id,
	u.name,
	u.age,
	o.number
from user u
left OUTER join
	orderlist o
ON 
	u.id=o.uid;
	
SELECT 
	u.id,
	u.name,
	u.age,
	o.number
FROM USER u
right OUTER JOIN
	orderlist o
ON 
	u.id=o.uid;

#
select 	
	newtable.*,
	o.number
from
	(select * from user where age>23) newtable,
	orderlist o
where 
	newtable.id=o.uid;
	
	
select
	newtable.*,
	o.number
from
	(select *  from user where name in('张三','李四')) newtable,
	orderlist o
where 
	newtable.id=o.uid;
	
select 
	c.id,
	c.NAME,
	p.NAME
from 
	category c
left join
	product p
on 
	c.id=p.cid;
*/	
	