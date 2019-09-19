
### 获取密钥详情
需要Java环境
keytool -list -v -alias master -keystore C:\StudioProjects\Master\app\lihang.jks

### 项目打印依赖
gradlew :app:dependencies
### 在kotlin项目中 注解处理器要使用kapt代替annotationProcessor，annotationProcessor（可能会失效？）
                             添加 apply plugin: 'kotlin-kapt'
                             
### 可变参数
{一个数组}或者{多个参数}都可以
### Intent
Intent.resolveActivity用来检查是否有隐式注册

### Toast如果show两次在某些机型（小米的部分手机）会不显示toast
                            
### SingleTask或singleInstance 如果在A启动A    虽然不会走大部分生命周期，但是会导致本身和位于其中的Fragment走onResume方法
    [HomeActivity, onActivityPaused]
    [HomeFragment, onFragmentPaused]
    [SupportRequestManagerFragment, onFragmentPaused]
    [HomeActivity, onActivityResumed]
    [HomeFragment, onFragmentResumed]
    [SupportRequestManagerFragment, onFragmentResumed]