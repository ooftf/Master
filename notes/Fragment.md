### 为什么出出现fragment重叠现象:
    因为activity内存回收的时候，fragment被FragmentManager保存了下来，当再次创建Activity的时候，原来被保存下来的fragment默认为显示状态
    解决方式：
1.    fragment保存显隐状态en
2.    activity重新创建的时候通过 tag找到原来fragment并重新设置显隐状态


### FragmentPagerAdapter和FragmentStatePagerAdapter
    FragmentPagerAdapter内fragment当移除视野外之后不会重新创建，会保存在内存中
    FragmentStatePagerAdapter 当fragment移除视野之外就会销毁
