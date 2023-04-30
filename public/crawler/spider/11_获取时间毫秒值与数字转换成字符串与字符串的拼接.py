import time
import calendar
st1r="1.png"
st1r=st1r+"hello"
print(st1r)
st1r="1"
suffix="png"
#使用"+"进行字符串的拼接
#使用str()方法将数字转换为字符串
ts = calendar.timegm(time.gmtime())
t=time.time()
print(int(round(t*1000)))
#任务延时后执行
time.sleep(5)
print(ts)
#原有内容会被删除。如果该文件不存在，创建新文件
path="E:\\home\\netbian"+str(100)+"."+suffix
print(path)