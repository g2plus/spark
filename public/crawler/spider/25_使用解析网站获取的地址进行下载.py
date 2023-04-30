import requests
headers={
    'User-Agent':
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/99.0.4844.74 Safari/537.36'
}
#使用解析网站获取页面解析html，获取地址，下载视频
url="https://apd-vlive.apdcdn.tc.qq.com/vipzj.video.tc.qq.com/gzc_1000102_0b535uaasaaax4ae4k4tlfq4b3odbhvqadka.f2.mp4?vkey=41FEF8B6FC71E7FEF3A2651ADF04BC4CDAFAB255DF8AACAF26DAA91887AE9125BF4980A051A3E4967378CD00A136BB9E611D15089CECD81FED0715850F8475FB11B590B2B315D624C4CF7F05531AE880DE4DB4305BE07DE13C0DFE76FCC48ED1300AEFA13211C9E195215948396C7CF6CAA940437A5ED6CE"
reponse=requests.get(url,headers=headers)
with open("test.mp4",'wb') as file:
    file.write(reponse.content)



