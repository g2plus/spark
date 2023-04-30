# '''
# conn=connect(参数列表)
# 参数host：mysql服务器的地址
# port：端口号
# 参数user 登录用户
# password 登录密码
# database 数据库的名称
# charset 通信采用的编码方式，推荐使用utf8
#
# 关闭连接 conn.close()
# 提交数据 conn。commit()
# 撤销数据 conn.rollback() 回滚
#
# 获取游标对象,执行sql语句，crud
# cur=conn.cursor()
# 使用游标对象执行sql语句 execute(operation [parameters]),返回影响的函数，主要用于执行select,delete,update,insert语句
# 获取结果集中的一条数cur.fectchone()返回一个元组 （1,”zhangsan"）
# 所有数据cur.fectchall()  (1,"xh") (2,"lisi")
# 关闭游标
# 粗人。close(),表示和数据库操作完成
#
# '''
#
#
import os.path
import pymysql
import requests
import time

#建立连接
conn=pymysql.connect(
    host='localhost',
    user='root',
    password='root',
    database='picture',
    charset='utf8')
cur=conn.cursor();
sql="SELECT DISTINCT url, id, website FROM netbian where id>=27145"
#返回影响的行数
row_cnt=cur.execute(sql)
print("sql影响的行数%d" %row_cnt)
headers={
    'User-Agent':
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36'}
#创建文件夹用于存放图片
dir="e:\\home\\bytedance\\"
flag=os.path.exists(dir)
if(not flag):
    os.makedirs(dir)

cnt=0
#获取数数据库库中的所有数据
for line in cur.fetchall():
    #print(type(line)) 数据类型为元组。
    #使用下标获取url地址，下标从0开始
    #print(line[0])
    #创建网络连接，爬取数据
    try:
        url=line[0]
        cnt=cnt+1;
        print("---正在下载第"+str(cnt)+"张图片...")
        response=requests.get(url,headers=headers)
        img=response.content
        t=time.time()
        suffix=os.path.splitext(line[0])[-1]
        path=dir+str(int(round(t*1000)))+suffix
        with open(path,'wb') as file:
            #自动关闭文件,底层实现
            file.write(img)
        print("第"+str(cnt)+"张图片下载完成")
    except Exception as ex:
        print("---出错继续---")
        pass
#关闭游标
cur.close()
#关闭连接
conn.close();
#
