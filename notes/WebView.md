# Process
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

