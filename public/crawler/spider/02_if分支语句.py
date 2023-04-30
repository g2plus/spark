# python中的int函数实现字符串转化成数字类型
# python使用input方法进行输入
#while循环
#boolean类型的取值True False
while True:
    try:
        x = int(input("Please enter a number: "))
        break #如果输入正确跳出循环
    except ValueError: #进行异常捕获
        print("Oops!  That was no valid number.  Try again...\n")
if x < 10:
    print(x, "number is less than 10")
else:
    print(x,"number is bigger than 10 or equal to 10" )