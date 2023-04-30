

#how to define function
def print_hi(name):

    # f的作用将name进行解析赋值，然后打印
    #print(f'{name}')其中 { } 相当于占位符，程序运行时花括号部分会变成相应的变量值，
    # 如果print('{name}')中 f 被去掉，就是普通的打印功能，原封不动地打印字符串 {name}
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.


# Press the green button in the gutter to run the script.
#程序的入口，规范
#详细参考一下link：https://www.zhihu.com/question/49136398
if __name__ == '__main__':
    print_hi(input())


