[![Build Status](https://www.travis-ci.org/ooftf/Master.svg?branch=dev)](https://www.travis-ci.org/ooftf/Master)
# Master
自己的开发工具集
## Task
### TODO LIST
* 如果只存才一种分辨率的图片，在不同分辨率下的渲染表现和内存表现
* Jetpack实践#############
* 内部集成RN
* 内部集成flutter
* Java内存整合
* DrawerLayout需要增加功能，实现实际大小的变化
* aspectj 深入学习
* LayoutManager
* 视频播放器
* 动态化构建页面 Tangram-Android vlayout
* MVVM Data Binding
* app bundles
* gradle插件
* sonar 代码质量检测
* 网关（Gateway）概念：又称网间连接器，网络协议转换器，适配器
* nio
* xmind
* 解决Rn so 包没有导入的问题
* 为progress-drawable添加文档
* 人工智能（TensorFlow）
* 语言切换
* 非入侵式埋点
* Hihttp addObserver没有强制主线程
* 编写脚本
* 全局dialog
* codecov
* 游戏引擎9
* aop : transform api
* 全链路协议（Dialog activity fragment）
* ASM
### DELAY LIST
* Ktor
* 吸顶效果代码需要封装
### DONE LIST
* 为什么会有两个启动图标: 是因为APP独立调试模式，包名起的为debug和debug模式重名，导致debug模式下编译将debug包名下的文件也编译进去了
* Rxjava compose :已经抽离网络请求；好处：将逻辑部分和界面部分分离，将不用界面处理分离做成更小的可以，使用更灵活
* 通知还没搞定: 通知已经能过够显示出来，但是悬浮通知还不知怎么做
* 兼容的水波纹效果 android:background="?android:attr/selectableItemBackground"
* ScrollerPlus转换成Rxjava去实现  （完成  SlidingLayout项目中用的就是）
* 测试DrawerLayout 内部是否支持RecycleView（已抽取为https://github.com/ooftf/SlidingLayout 支持RecycleView）
* 约束布局
* 将周饭计算器合并到此工程
* LruCache
* Java8新特性
* 拦截器设计模式
* 策略设计模式
* 高斯模糊
* SpialeLayout有点问题,重写布局
* 懒加载Fragment抽离
* 悬浮通知
* 曲边控件:已经用贝塞尔曲线实现
* 线程池
* 注解处理器：docking
* RxJava深度学习
* maven 发布流程：Bintray
* node.js概念
* 依赖注入
* 贝塞尔曲线 bezier https://github.com/MrAllRight/BezierView  https://github.com/leeowenowen/beauty-of-math
* Androidx(升级master项目非常方便，无痛升级，google dad)
* AutoRegister （很强）
* protobuf转换项目 传统protobuf和pojo  （pojo  暂时没有找到方法自动生成）
* （难题） 方法中的一个参数，如何用两个接口定义 ,解决方式：可用<T extends Callback & ILifecycleState>泛型的这个特性，解决部分需求，不能完全解决
* 扫一扫
* 悬浮求
* RxJava中 flatmap 和doOn... 会不会导致内存泄漏（结论：会，但是如果用Java8 lamdba 表达式或者kotlin箭头函数，会根据是否包含外部引用从而生成匿名内部类或静态匿名内部类，从而一定情况避免内存泄漏）
* Material Button 暂时没有发现有什么重要的作用
* travis  本项目以完成初步集成
* DialogFragment   将逻辑封装在Dialog内部
## Introduce
     MVP + Dagger = Sign