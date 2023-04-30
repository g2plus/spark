import requests
#获取后端发送的cookie关联项目java web
url = 'https://www.baidu.com'
headers={'User-Agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36'}
r = requests.get(url,headers=headers)
r.cookies['BAIDUPSID']

#java web实现cookie操作。
url = 'http://httpbin.org/cookies'
cookies = dict(cookies_are='working')
r = requests.get(url, cookies=cookies)
r.text