# 查看端口号是否被占用    
    1.netstat  -anp  |grep   端口号
    2.netstat -ntulp |grep 80
# 查看已被占用端口号
    netstat   -nultp
    
#  netstat -an | grep 8890