# 在没有使用RxJava时我们遇到了什么问题
## 确认问题
    * 回调式网络请求会降低代码可读性
    * 切换线程十分麻烦
    * 网络请求如果操作不当会导致内存泄漏，处理内存泄漏的方式比较麻烦（静态类+弱引用  和  将取消事件传递到导致泄漏的引用层，解除引用）
# 为什么要使用RxJava
    ## RxJava是如何解决上述问题的
    ## RxJava还能做什么
        * 各种操作符
        * 计时器
# 什么是RxJava
## 字面意思
    Reactive Extensions
    r = rective 函数响应式编程 异步数据流
    x = x
# RxJava 中各种元素介绍
## 可观察对象
    * io.reactivex.Flowable: 0..N flows, supporting Reactive-Streams and backpressure
    * io.reactivex.Observable: 0..N flows, no backpressure,
    * io.reactivex.Single: a flow of exactly 1 item or an error,
    * io.reactivex.Completable: a flow without items but only a completion or error signal,
    * io.reactivex.Maybe: a flow with no items, exactly one item or an error.
## 操作符
## 线程切换
## 订阅者
# RxJava 实际应用

# RxJava中可能存在的一些疑惑
# 使用RxJava曾遇到的问题（需要注意的问题）
# RxJava资料链接
# RxJava问题讨论
