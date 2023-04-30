import requests
#r is a response obj
r = requests.get("https://pic.netbian.com/index_2.html")
#print(r.content.decode('gbk'))
print(r.request.headers)#打印请求头的信息
print(r.headers)#打印响应头的信息
print(r.encoding)#打印编码方式
print(r.cookies)#cookiejar
print(r.headers['content-type'])
print(r.url)#查看请求路径

payload = {'wd': '中华人民共和国'}
r=requests.get("https://www.baidu.com/s",params=payload)#发送get请求
print(r.url)
print(r.status_code)#答应响应码 网站可以自己设置响应码

#def post(url, data=None, json=None, **kwargs):
#不带文件操作
payload = {'query': 'Hello'}
r=requests.post("https://fanyi.baidu.com",data=payload)
print(r.content)
print(r.text)


#设置指定的请求头信息
url="https://pic.netbian.com/"
headers={"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36"}
r=requests.get(url,headers=headers)
print(type(r.text))

#同一key传递多个函数
payload = (('key1', 'value1'), ('key1', 'value2'))
r = requests.post('http://httpbin.org/post', data=payload,headers=headers)
print(r.request.url)
print(r.text)

