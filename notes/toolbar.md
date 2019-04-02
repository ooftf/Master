# OptionMenu遮挡按钮问题，修改OptionMenu背景颜色
```java 
setPopupTheme(R.style.ThemeOverlay_Toolbar_PopupMenu);

<style name="ThemeOverlay.Toolbar.PopupMenu" parent="ThemeOverlay.AppCompat.Light">
        <item name="overlapAnchor">false</item>
</style>
```
# 修改toolbar字体颜色，修改toolbar图标颜色
```


修改MenuItem字体颜色,设置在Activity的Theme属性上面
<item name="actionMenuTextColor">@color/colorPrimaryContrast</item>


设置PopupTheme
修改 OptionMenu 文字颜色,不会修改ItemMenu字体颜色 <item name="android:textColor">@color/black</item>
```