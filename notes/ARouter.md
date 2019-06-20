[中文文档](https://github.com/alibaba/ARouter/blob/master/README_CN.md)
## path  只要需要两级，不然会报一些莫名其妙的错误比如  Javac
### 路由中的分组概念
SDK中针对所有的路径(/test/1 /test/2)进行分组，分组只有在分组中的某一个路径第一次被访问的时候，该分组才会被初始化
可以通过 @Route 注解主动指定分组，否则使用路径中第一段字符串(/*/)作为分组
注意：一旦主动指定分组之后，应用内路由需要使用 ARouter.getInstance().build(path, group) 进行跳转，手动指定分组，否则无法找到
不同 module 之间不能用同一个分组
# service 是以单例的形式存在的
# withSerializable并不能用Autowired获取传递的值，而withObject可以
# 使用withLong 传递的参数可以用long接收但是Long接受不到

# 只需要在主项目中添加  apply plugin: 'com.alibaba.arouter'   //应该是，没有得到官方回复
# proguard 
    在使用注解Autowired生成服务的时候，需要添加@keep