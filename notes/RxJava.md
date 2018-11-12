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
   ### 生成observable
            * just
            * create 异步
            * fromIterable 集合
            * fromArray 数组
            * interval 定时器
            * intervalRange 定时器 固定次数
            * timer 延迟执行
   ### 操作 Observable
           * map 一个数据转为另一个数据
           * flatMap 一个 数据 转换成一个 Observable :需要第一个数据源得到后异步获取
           * concatMap 和flatMap 类似，只不过只有当 上一个执行完onComplete 才会执行下一个
           * compose 一个Observable 转换成另一个Observable ;可以将一些“操作步骤”进行封装
           * toList:将多个onNext 生成一个 Single
            类似切面编程
           * doOnSubscribe 在 onSubscribe 之前做一些动作
           * doOnNext: 再onNext 之前做一些动作
           * doOnComplete 在 onComplete之前做一些动作
           * doAfteSubscribe
           * doAfterNext 类似
           * doAfteComplete


           线程切换
           observeOn
           subscribeOn

   ### 合并observable
        合并成 min onNext
            zip
        合并后onNext相加
            concat  第一个执行完onComplete 才会执行第二个
            merge   按照onNext的时间顺序 交叉合并

## 订阅者
    Observer
    Customer
# RxJava 实际应用

# RxJava中可能存在的一些疑惑
     Observable.create(new ObservableOnSubscribe<Object>() {
                @Override
                public void subscribe(ObservableEmitter<Object> emitter) throws Exception {

                }
            });
     中subscribe什么时候执行？  observable.subscribe()的时候

    线程问题
    在不主动切换线程的情况下  获取数据的线程就是 emitter.onNext 所在线程

# 使用RxJava曾遇到的问题（需要注意的问题）
# RxJava资料链接
# RxJava问题讨论
