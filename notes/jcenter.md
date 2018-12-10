#### HTTP/1.1 404 Not Found [message:Repo ‘maven’ was not found]
publish {
    repoName = 'maven' // 默认是maven，如果不是maven需要指定
    userOrg = 'ooftf'      // bintray注册的用户名
    groupId = 'com.ooftf'
    artifactId = 'docking-compiler'// bintray创建的package
    publishVersion = '1.0.0'
    desc = 'nothing'
}
#### HTTP/1.1 400 Bad Request [message:Please enter a valid VCS URL for your OSS package.]
allprojects {
    tasks.withType(Javadoc) {
        options{
            encoding "UTF-8"
            charSet 'UTF-8'
            links "http://docs.oracle.com/javase/7/docs/api"
        }
    }
}
#### 报错Javadoc乱码问题
在module gradle中添加
tasks.withType(Javadoc) {
    options.addStringOption('Xdoclint:none', '-quiet')
    options.addStringOption('encoding', 'UTF-8')
    options.addStringOption('charSet', 'UTF-8')
}

#### 报错 jvm heap相关
删除 org.gradle.jvmargs=-Xmx1536m
