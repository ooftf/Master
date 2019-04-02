# Process
## 什么是进程
进程系统进行资源分配和调度的基本单位，是程序在内存中的实例;
进程之间内存相互独立


## 为什么要多进程
Android对单个进程是有内存大小限制的，多进程可以申请更多的内存
可以减少单个进程的内存大小
进程间崩溃不会相互影响
可以做进程保活
## 多进程会存在那些问题
内存相互独立
    ActivityLifeCycleCallbacks 只能拿到各自进程Activity的生命周期
    单例等静态资源会存在多份
    多进程会初始化多个Application
    sp多进程无法及时同步
多进程在同时读写文件的时候会出问题
sp,db
### cookie 能否sync
原生 CookieManager cookie 可以同步
X5  CookieManger cookie 不可以
### A 进程 start activity 是否在A进程中
    start的activity在哪个线程和从哪个activity start无关，但是如果进程模式事MultiProcess就是从哪个进程启动就存在于哪个进程
### SharedPreferences 能否跨进程
    不可以,如果提交方式未apply则可以同步一次，这是一个假象，最终结果是不可以
    解决方案 ContentProvider或者Aidl桥接或者MMKV
    
## Messenger
底层依然是AIDL，加上Handle的一种封装
## AIDL
AIDL + Service实现跨进程通讯
AIDL 文件同一个interface 不可以有两个同名方法，即使参数不同也不可以

![AIDL流程图](http://assets.processon.com/chart_image/5c6f5920e4b0f0908a9d2f6d.png)
