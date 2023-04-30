from selenium import webdriver
import time
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.common.by import By
from selenium.webdriver.common.action_chains import ActionChains
url="http://tool.liumingye.cn/video/"
driver=webdriver.Chrome()
#am-btn am-btn-primary

driver.get(url)
input=driver.find_element_by_xpath("//input[@name='url']")
input.send_keys("https://v.qq.com/x/cover/mzc00200gw2ez0b/d0042je4ryh.html")

button1=driver.find_element_by_xpath("//button[@class='am-btn am-btn-primary']")
button1.click()
print(driver.page_source)


