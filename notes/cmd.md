
### 获取密钥详情
keytool -list -v -alias master -keystore C:\StudioProjects\Master\app\lihang.jks

### 项目打印依赖
gradlew :app:dependencies
### 在kotlin项目中 注解处理器要使用kapt代替annotationProcessor，annotationProcessor（可能会失效？）
                             添加 apply plugin: 'kotlin-kapt'
                             
### 可变参数
{一个数组}或者{多个参数}都可以
### Intent
Intent.resolveActivity用来检查是否有隐式注册
                            

