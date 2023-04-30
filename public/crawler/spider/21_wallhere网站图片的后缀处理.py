import os
fileName="2.jpg!s"
suffix=os.path.splitext(fileName)[-1]
print(type(suffix))
#使用split方法获取进行字符串的切分
print(suffix.split("!"))
print(type(suffix.split("!")[0]))
print(suffix.split("!")[0])


#os.path.splitext(line[0])[-1].split("!")[0]

url="https://c.wallhere.com/images/49/07/431cddc71cc126d5f92d51cf6a99-1276609.png!s".split("!")[0]+"!d"
print(url)
target=os.path.splitext(fileName)[-1].split("!")[0]
print(type(target))
print(target)
