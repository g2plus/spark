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
sql="SELECT DISTINCT url, id, website FROM wallhere where id>=292"
#返回影响的行数
row_cnt=cur.execute(sql)
print("sql影响的行数%d" %row_cnt)
headers={
    'User-Agent':
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36'}
#创建文件夹用于存放图片
dir="e:\\home\\bytedance\\wallhere\\"
flag=os.path.exists(dir)
if(not flag):
    os.makedirs(dir)

cnt=0
for line in cur.fetchall():
    #print(type(line)) 数据类型为元组。
    #使用下标获取url地址，下标从0开始
    #print(line[0])
    #创建网络连接，爬取数据
    try:
        url=line[0].split("!")[0]+"!d"
        cnt=cnt+1;
        print("---正在下载第"+str(cnt)+"张图片...")
        response=requests.get(url,headers=headers)
        img=response.content
        t=time.time()
        suffix=os.path.splitext(line[0])[-1].split("!")[0]
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