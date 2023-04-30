import requests
import json

#方式一导入 json
url = 'https://api.github.com/some/endpoint'
payload = {'some': 'data'}
#调用模块的dumps方法
r = requests.post(url, data=json.dumps(payload))


url = 'https://api.github.com/some/endpoint'
payload = {'some': 'data'}
r=requests.post(url, json=payload)
print(r.request.body)#打印请求体信息

url = 'http://httpbin.org/post'
#open指定方式读取文件,mime
files = {'file': ('report.xls', open('report.xls', 'rb'), 'application/vnd.ms-excel', {'Expires': '0'})}
r = requests.post(url, files=files)
print(r.text)