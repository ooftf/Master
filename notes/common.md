<string name="leak_canary_notification_dumping">Dumping Heap</string>

## Manifest merger failed with multiple errors  MMKV 最低版本16


## 如果断点放到return 语句上 会走两次
## compileSdkVersion 决定了查看源码的版本
---
## 签名选择V1和V2的时候要，两个都选，如果只选v1，没有v2安全，如果只选v2有可能在android低版本上安装出现[install_parse_failed_no_certificates]
---
## 在保存图片到本地的需求中，最好将图片保存在一级目录，因为在小米系统中保存在一级目录   （MyImage/sample.png）在打开相册是会提示新图片，但是如果保存到二级目录              （MyImage/download/sample.png）中则不不会有提示

# 类的内部各个部分调用顺序
    （加载类）静态变量赋值 ---> 静态代码块 --（实例化）-->  进入构造方法 ---> super构造方法 ---> 非静态变量赋值---> 非静态代码块---> 构造方法实体 
# LocalMaven publish 后  主项目不需要同步和Clean直接run就可以

# 手机抓包教程 https://www.jianshu.com/p/724097741bdf

# 渐变色 https://uigradients.com/#Opa

# ObservableField和LiveData set同一个对象  不会触发change事件，可以用ObservableField 的notifyChange  ?????

# onCreate(进入)->Application.ActivityLifecycleCallbacks.onActivityCreated->onCreate(出)->LifecycleObserver.onCreate
# LifecycleObserver.destroy->onDestroy(进入)->Application.ActivityLifecycleCallbacks.onActivityDestroyed->FragmentLifecycleCallbacks.onFragmentViewDestroyed+onFragmentDestroyed->onDestroy(出)
# LiveData设置同一个对象，也会触发change事件，LiveData.getValue的初始值为null
# ObservableField设置同一个对象，不会触发change事件,需要用的notifyChange触发.设置不同的对象可以触发；ObservableField.get()初始值为null
# LiveData和ObservableField的内部属性设置新值都不会触发改变