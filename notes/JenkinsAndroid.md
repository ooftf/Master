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


### Problem
#### package javax.annotation does not exist 和 cannot find symbol @Generated
     出现原因：JDK版本大于等于9导致
     解决方式：1.降低JDK版本   2.添加 compile 'org.glassfish:javax.annotation:10.0-b28'
#### Gradle编译突然失败没有任何错误日志
     出现原因：centos 内存不够
     解决方式：增加内存
