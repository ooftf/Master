package com.ooftf.service.engine.router;

import android.net.Uri;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 将开启 activity的意图序列化为 Bundle 对象用于 activity之间的传递
 * Scenes: 当某个【开启activity的意图】被 拦截器拦截掉，就会跳转到登录页面，登录成功时候要继续原来【开启activity的意图】
 * @author lihang9
 */
public class PostcardSerializable {

    public static final String URI = "Uri";
    public static final String EXTRAS = "Extras";
    public static final String PATH = "Path";

    public static Bundle toBundle(Postcard postcard) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(URI, postcard.getUri());
        bundle.putBundle(EXTRAS, postcard.getExtras());
        bundle.putString(PATH, postcard.getPath());
        return bundle;
    }

    public static Postcard toPostcard(Bundle bundle) {
        Uri uri = bundle.getParcelable(URI);
        String path = bundle.getString(PATH);
        Bundle extras = bundle.getBundle(EXTRAS);
        return ARouter.getInstance().build(path).with(extras).setUri(uri);
    }
}
