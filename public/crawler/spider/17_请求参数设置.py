import requests
import json
import time
import os
url = "https://wallhere.com/"
headers = {
    'User-Agent':
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36'
}

dir="e:\\home\\html\\"
flag=os.path.exists(dir)
suffix=".html"
if(not flag):
    os.makedirs(dir)
for i in range(500, 600, 1):
    try:
        page = i + 1
        params = {
            "page": page,
            "format": "json"
        }
        response = requests.get(url, headers=headers, params=params)
        #print(json.loads(response.text)["data"])
        print("------------------------------------------")
        #内容写入文件
        target = dir+str(page) + suffix
        file_handle=open(target,mode='wb')
        file_handle.write(json.loads(response.text)["data"].encode("utf-8"))
        file_handle.close()
        #time.sleep(2)
    except Exception as ex:
        print("---出错继续---")
        pass

