import os
fileName="https://pic.netbian.com/uploads/allimg/161008/104819-1475894899ea15.jpg"
print(type(os.path.splitext(fileName)[-1]))
#带点的文件名后缀
print(os.path.splitext(fileName)[-1])