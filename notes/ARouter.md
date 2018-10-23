[中文文档](https://github.com/alibaba/ARouter/blob/master/README_CN.md)
## path
### 路由中的分组概念
SDK中针对所有的路径(/test/1 /test/2)进行分组，分组只有在分组中的某一个路径第一次被访问的时候，该分组才会被初始化
可以通过 @Route 注解主动指定分组，否则使用路径中第一段字符串(/*/)作为分组
注意：一旦主动指定分组之后，应用内路由需要使用 ARouter.getInstance().build(path, group) 进行跳转，手动指定分组，否则无法找到
不同 module 之间不能用同一个分组

# service 是以单例的形式存在的