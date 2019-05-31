# 注意
* 部分使用伪代码
# 在没有使用RxJava时我们遇到了什么问题
## 确认问题
    * 回调式网络请求会降低代码可读性
    * 切换线程十分麻烦
    * 网络请求如果操作不当会导致内存泄漏，处理内存泄漏的方式比较麻烦（静态类+弱引用  和  将取消事件传递到导致泄漏的引用层，解除引用）
# 为什么要使用RxJava
## RxJava是如何解决上述问题的
## RxJava还能做什么
    各种操作符（十分强大）
    计时器
# 什么是RxJava
## 字面意思
    Reactive Extensions
    r = rective 函数响应式编程 异步数据流
    x = Extensions
# RxJava 中各种元素介绍
## 可观察对象
    * io.reactivex.Flowable: 0..N flows, supporting Reactive-Streams and backpressure
    * io.reactivex.Observable: 0..N flows, no backpressure,
    * io.reactivex.Single: a flow of exactly 1 item or an error,
    * io.reactivex.Completable: a flow without items but only a completion or error signal,
    * io.reactivex.Maybe: a flow with no items, exactly one item or an error.
## 操作符
   ### 创建observable
            * just
            * create 异步
            * fromIterable 集合
            * fromArray 数组
            * interval 定时器
            * intervalRange 定时器 固定次数
            * timer 延迟执行
           高级
                defer  将获取数据流的操作延迟到订阅时刻
                fromCallback 将获取数据流的操作延迟到订阅时刻和defer的区别在于这个只能是一次事件
   ### 操作 Observable
           * map 一个数据转为另一个数据
           * flatMap 一个onNext数据 转换成一个 Observable :需要第一个数据源得到后异步获取
           * concatMap 和flatMap 类似，只不过只有当 上一个执行完onComplete 才会执行下一个
           * compose 一个Observable 转换成另一个Observable ;可以将一些“操作步骤”进行封装
           * toList:将多个onNext 生成一个 Single
            类似切面编程
           * doOnSubscribe 在 onSubscribe 之前做一些动作
           * doOnNext: 再onNext 之前做一些动作
           * doOnComplete 在 onComplete之前做一些动作
           * doAfterSubscribe
           * doAfterNext 类似
           * doAfterComplete


           线程切换
           observeOn: 切换observeOn 之后的线程
           subscribeOn 切换observeOn 之前线程；如果不存在observeOn就是所有线程；当出现多个subscribeOn的时候只有第一个生效

   ### 合并observable
        合并成 min onNext
            zip
        合并后onNext相加
            concat  第一个执行完onComplete 才会执行第二个
            merge   按照onNext的时间顺序 交叉合并

## 订阅者
    Observer
    Consumer
# RxJava 实际应用
    数据变换处理
    多层网络请求
# RxJava中可能存在的一些疑惑
     Observable.create(new ObservableOnSubscribe<Object>() {
                @Override
                public void subscribe(ObservableEmitter<Object> emitter) throws Exception {

                }
            });
     中subscribe什么时候执行？  observable.subscribe()的时候

    线程问题
    在不主动切换线程的情况下  获取数据的线程就是 emitter.onNext 所在线程
    订阅线程（ObservableOnSubscribe.subscribe）所在线程 在没有主动切换线程的情况下就是是创建Observable所在线程
### 问题：在下面情况下 observeOn 控制的是哪一层 ，doOnNext 又是控制的哪一层
    Observable.just()
    .flatmap
    .faltmap
    .faltmap
    .observeOn
    结论：
    * doOnNext 作用的是当前Observable,doOnSubscribe作用于整个流程的最开始（最初的Observable）
    * observeOn 作用于observeOn之后的部分
### 问题在 observeOn subscribeOn 后 doOnNext doOnSubscribe doOnError 是在哪个线程
### RxJava 和传统观察者模式有什么不同
    RxJava 是数据流操作 定义的是数据之间的变换关系
    观察者模式，通常是基于事件
* 观察者：只有先订阅才能接收到事件；RxJava 订阅后才发射数据
* 观察者同一事件能被所有订阅者接收，而在RxJava中一个observer对应一个emitter，一个emitter发射出去的数据只能被一个observer接收

# PublishSubject 更类似于传统观察者模式

多次订阅一个Observable 事件是分开的，因为每次订阅都会生成不同的订阅器
多次订阅PublishSubject 事件是相同的，可以多次订阅，类似传统观察者模式，但是onError和onComplete会阻断后续事件

# 使用RxJava曾遇到的问题（需要注意的问题）
* subscribe(Consumer())这种订阅没有做异常处理，如果发生异常会导致应用崩溃
* RxJava只能捕获当前同步异常,如下情况是无法通过RxJava捕获的
    Observable.create {
             Thread{
                  exception
             }
     }
* 如果 disposable.dispose 之后 还执行了emitter.onError()就会因为异常没有捕获而导致应用崩溃，所以emitter使用之前需要判断一下emitter.isDisposed
* doOnError 不能防止异常外抛
* doOn... 等事件中要做防内存泄漏问题，RxLifecycle disposable 并不能防止这种形式的内存泄漏，具体操作可以参照 hi-http
* 如果doOn事件中需要在主线中操作，要在doOn之前调用线程切换
# RxJava资料链接
    https://github.com/ReactiveX/RxJava
    https://github.com/ReactiveX/RxAndroid        切换主线程
    https://github.com/JakeWharton/RxBinding      对Android View事件的扩展，
    https://github.com/tbruyelle/RxPermissions    Android 权限管理
    https://github.com/trello/RxLifecycle         生命周期管理，及时取消订阅
    https://github.com/AndroidKnife/RxBus         事件总线
    https://github.com/JakeWharton/RxRelay        RxJava 在发射 onError或者onComplete数据后将不能在发射后续数据，RxRelay就是为了解决这个问题而产生的
# RxJava问题讨论
    再合并多个Observer的时候线程切换是什么结果
    onNext 时间抛出异常会中断数据流吗
# TODO
* blocking
* retry
* repeat
* throttle 节流
* debounce 去抖
* throttleFirst
* sample
* retryWhen   onComplete事件



