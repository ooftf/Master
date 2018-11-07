### Problem
* android 8.0 为什么不显示：没有设置NotificationChannel
* setSmallIcon 为什么不管用：在原生系统为右边小图标，在定制系统中有可能是不显示的（已知 MIUI 10不显示）
* setLargeIcon 设置左侧大图标

* setSmallIcon 为什么不管用：在原生系统为右边小图标，在定制系统中有可能是不显示的（已知 MIUI 10不显示） 如果打开有通知时显示图标，会在statebar 显示小图标

* channel 创建一次跟随应用的整个生命周期，从安装到卸载,除非删除

* channel 创建后(name 和 Description 可以修改)( importance ShowBadge BypassDnd 不可修改) 其他未证实

* 显示悬浮通知：  channel 的 importance 至少要是NotificationManager.IMPORTANCE_DEFAULT 也就是3