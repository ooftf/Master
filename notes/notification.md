### Problem
* android 8.0 为什么不显示：没有设置NotificationChannel

* setLargeIcon 设置左侧大图标 在没有设置的情况下会显示app icon

* setSmallIcon 为什么不管用：在原生系统为右边小图标，在定制系统中有可能是不显示的（已知 MIUI 10不显示） 如果打开有通知时显示图标，会在statebar 显示小图标
## channel
* channel 创建一次跟随应用的整个生命周期，从安装到卸载,除非删除

* channel 创建后(name 和 Description 可以修改)( importance ShowBadge BypassDnd 不可修改) 其他未证实

## 显示悬浮通知：
* channel 的 importance 至少要是NotificationManager.IMPORTANCE_DEFAULT 也就是3
* setContentIntent 设置悬浮通知和通知的点击事件，不会导致悬浮通知失效
* 不会导致悬浮通知失效：setContentTitle setContentText setLargeIcon setSmallIcon
  addAction setNumber setContentIntent setAutoCancel setTicker setWhen setOnlyAlertOnce
* 会导致悬浮通知失效：setFullScreenIntent
* setCustomHeadsUpContentView 可以自定义悬浮通知的样式，会不改变普通通知的样式