import requests
import os
import time
import json
from bs4 import BeautifulSoup
url="https://wallhere.com/?page=1&format=json"
headers={
    'User-Agent':
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36'
}
response=requests.get(url,headers=headers,params=params)
#https://blog.csdn.net/tterminator/article/details/63289400
print(type(json.loads(response.text)))
#获取字典中某个key对应的value
print(json.loads(response.text)["data"])

print(type(json.loads(response.text)["data"]))
