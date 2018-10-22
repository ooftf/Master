package com.ooftf.service.engine.router;

import android.net.Uri;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;

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
