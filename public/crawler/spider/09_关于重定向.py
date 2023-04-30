import requests
r = requests.get('https://www.baidu.com')
print(r.url)
print(r.status_code)
print(r.history)

print("---------------------------------------------------")
#禁用重定向
r = requests.get('http://github.com', allow_redirects=False)
print(r.status_code)

print("---------------------------------------------------")
#响应时间0.001秒内，否则断开连接
#timeout 仅对连接过程有效，与响应体的下载无关
headers={"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36"}

r=requests.get('https://www.baidu.com', timeout=0.05, headers=headers)
print(r.status_code)

print("---------------------------------------------------")
#响应时间0.001秒内，否则断开连接
#timeout 仅对连接过程有效，与响应体的下载无关
#headers={"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36"}
r=requests.get('https://pic.netbian.com/index_2.html', timeout=0.05, headers=headers)
print(r.status_code)

