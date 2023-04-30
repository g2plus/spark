import os.path
import pymysql
import requests
import time
from bs4 import BeautifulSoup
#建立连接
conn=pymysql.connect(
    host='localhost',
    user='root',
    password='root',
    database='picture',
    charset='utf8')
cur=conn.cursor();
sql="SELECT DISTINCT url FROM wallhaven WHERE id>=1092"
#返回影响的行数
row_cnt=cur.execute(sql)
print("sql影响的行数%d" %row_cnt)

headers={
    'User-Agent':
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36'
}

#创建文件夹用于存放图片
dir="e:\\home\\bytedance\\wallhaven\\"
flag=os.path.exists(dir)
if(not flag):
    os.makedirs(dir)
cnt=0;
#白遍历所有数据
for line in cur.fetchall():
    #print(type(line)) 数据类型为元组。
    #使用下标获取url地址，下标从0开始
    #print(line[0])
    #创建网络连接，爬取数据
    try:
        #line的数据类型为tuple。不做任何处理，No connection adapter for ”https:https://wallhaven.cc/w/g7j8mq,“
        url=line[0]
        cnt=cnt+1
        print("---正在下载第" + str(cnt) + "张图片...")
        response=requests.get(url,headers=headers)
        #使用内置的html解析器
        soup=BeautifulSoup(response.text,'html.parser')
        #查找id为wallpaper的img标签
        src=soup.find('img', id = 'wallpaper').get('src')
        #获取到图片的yrl路径，发起请求,并设置超时时间
        response=requests.get(src,headers=headers,timeout=3)
        #获取响应的二进制流
        img = response.content
        #获取当前时间的毫秒值
        t = time.time()
        #获取文件的后缀名
        suffix=os.path.splitext(src)[-1]
        path=dir+str(int(round(t*1000)))+suffix
        with open(path,'wb') as file:
            #自动关闭文件,底层实现
            #写入文件
            file.write(img)
        print("第"+str(cnt)+"张图片下载完成")
        time.sleep(0.5)
    except Exception as ex:
        print("---出错继续---")
        pass
#关闭游标
cur.close()
#关闭连接
conn.close();