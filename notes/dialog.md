# 设置dialog大小
```
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) (getContext().getResources().getDisplayMetrics().widthPixels * percent);
        getWindow().setAttributes(attributes);
```
必须在设置view之后调用，不然不起作用

# 设置背景
 <item name="android:backgroundDimEnabled">false</item>  背景是否变暗
<item name="android:backgroundDimEnabled">true</item> 和 <item name="android:windowBackground">@null</item> 同时存在会导致背景变为"电视没信号的样子"
  <item name="android:windowBackground">@color/transparent</item> 效果等同于 getWindow().getDecorView().setBackground(null) 等同于  getWindow().setBackgroundDrawable(null);
  是设置dialog本身大小的背景，不是整个"变暗部分"
  * 如果想要透明背景就要把<item name="android:backgroundDimEnabled">false</item> 设置为false
# show Dialog后状态栏变成黑色
如果把 getWindow().getAttributes() 的宽高 都设置成MATCH_PARENT 就会导致状态栏变成黑色
