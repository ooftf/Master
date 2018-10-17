# 设置dialog大小
```
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) (getContext().getResources().getDisplayMetrics().widthPixels * percent);
        getWindow().setAttributes(attributes);
```
必须在设置view之后调用，不然不起作用