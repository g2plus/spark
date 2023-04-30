#导入os模块进行创建文件夹
import os
path="e:\\home\\bytedance\\"
folder=os.path.exists(path)
if(not folder):
    os.makedirs(path)