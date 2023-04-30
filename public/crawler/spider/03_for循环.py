lista=[1,2,3,4]
sum=0
for item in lista:
    sum+=item
print(sum)

tupplea=(1,2,3,4)
sum2=0
for item2 in tupplea:
    sum2+=item2
print(type(sum2))
print(sum2)

listb=['1','1','0']
str=''
for item3 in listb:
    str+=item3
print(type(str))
print(str)

dicta = {'name':'孙悟空','age':18,'gender':'男'}
print(dicta.keys()) #获取字典中所有的key，并打印
print(dicta.values()) #获取字典的所有value，并打印
print()
# 通过遍历keys()来获取所有的键
for k in dicta.keys() :
    print(k,dicta[k])

number=(1,2,3,4)

#else的代码块语句中 for没有跳出的情况下会被执行
#如果中途退出,else中的代码不会执行
num=10
for item in number:
    print(num)
else:
    print(num,"is not in tupple number")

for item2 in number:
    if num!=item2:
        break
else:
    print("for循环发生break，我不会打印哟")

for item2 in number:
    if num!=item2:
        continue
else:
    print("hello world")