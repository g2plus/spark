1.创建连接

2.点击连接图片，create database

3.创建好database之后,新建集合collection

4.创建collection之后，创建文档document 

db.getCollection('book').find({})#查询所有的记录

db.book.save({"name":"java8实战"})# 保存插入数据 db.集合名称.insert/save/insertOne(文档)

db.集合名称.remove(条件)

db.集合名称.update(条件，{操作种类:{文档}})