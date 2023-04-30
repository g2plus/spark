1.上传jdk到服务器内部

2.修改 /etc/profile
添加一下内容
export JAVA_HOME=/usr/local/develop/jdk1.8.0_261
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar

