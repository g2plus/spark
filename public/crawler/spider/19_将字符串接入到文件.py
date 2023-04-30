import os
str="Hell world!"
str=str.encode("utf-8");

fileName="test.html"
#文件不存在则自动创建文件
file_handle=open(fileName,mode='w')
file_handle.write(str.decode("utf-8"))
file_handle.close()