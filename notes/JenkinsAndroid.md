    Gradle task  build表示编译所有版本
    勾选   Use Gradle Wrapper
    勾选   Force GRADLE_USER_HOME to use workspace	解决gradle路径过长问题
    assemble --stacktrace --debug
### Gradle 配置
    Gradle task  build表示编译所有版本
    勾选   Use Gradle Wrapper
    勾选   Force GRADLE_USER_HOME to use workspace	解决gradle路径过长问题
    assemble --stacktrace --debug
    task不区分大小写assembledebug  也是可以的
#### [蒲公英配置](http://www.pgyer.com/doc/view/jenkins_plugin)

pgyer api_key    直接点问号
scandir  ${WORKSPACE}\app\build\outputs\apk\${build_type}
file wildcard  *.apk
buildInstallType(optional)  1   代表public
qrcodePath(optional)  ${WORKSPACE}\app\build\outputs\qrcode\${BUILD_TYPE}\${BUILD_TIME}\qrcode.png    二维码保存地址
### Email
Project Recipient List  收件人列表
Attachments ${WORKSPACE}\app\build\outputs\apk\${build_type}\app-${build_type}.apk   附件，错误不能使用绝对地址
app\build\outputs\apk\${build_type}\app-${build_type}.apk,app\build\outputs\qrcode\${BUILD_TYPE}\qrcode.png
Extended E-mail Notification 和邮件通知是两个不同的功能，都需要各自配置自己的账号信息

##### 阿里云 发邮件失败
端口改为465  勾选SSL

### CentOS 修改Jenkins端口号
    vim /etc/sysconfig/jenkins
    JENKINS_USER 改为 "root"  //一定要改用户，之前一直没有改成功就是应为没有改用户
    JENKINS_PORT 改为 "端口号"
    

### CentOS安装Android SDK
    echo y | ./sdkmanager "build-tools;28.0.3" "platforms;android-28"
### Problem
#### package javax.annotation does not exist 和 cannot find symbol @Generated
     出现原因：JDK版本大于等于9导致
     解决方式：1.降低JDK版本   2.添加 compile 'org.glassfish:javax.annotation:10.0-b28'
#### Gradle编译突然失败没有任何错误日志
     出现原因：centos 内存不够
     解决方式：增加内存
     
#### 有可能是内存过低
    Compilation with Kotlin compile daemon was not successful
    java.rmi.UnmarshalException: Error unmarshaling return header; nested exception is: 
        java.io.EOFException

#### What went wrong: Gradle build daemon disappeared unexpectedly (it may have been killed or may have crashed)
    系统内存不足导致，回收掉了守护线程
    在 gradle.properties 中添加 org.gradle.parallel=true 导致的，删掉就可以了
    org.gradle.daemon=false
#### 参数化构建中，参数的名字一定要写成大写的，不然某些插件就会出问题，比如蒲公英


#### MessagingException message: IOException while sending message
    邮件添加附件的时候出问题，文件比较大导致上传时间超时。
    解决方法：增加阿里云宽带
  


### Webhook https://blog.csdn.net/xiaosongluo/article/details/80151474        (这个网址有不少错误，但是可以用)
### SendFailedException message: 550 Error: content rejected.http://mail.qq.com/zh_CN/help/content/rejectedmail.html
    qq邮箱发送太过频繁，被qq邮箱服务器封ip了