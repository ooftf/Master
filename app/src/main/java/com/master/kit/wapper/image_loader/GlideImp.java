package com.master.kit.wapper.image_loader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by master on 2017/1/22.
 */

public class GlideImp implements IImageLoader {
    @Override
    public void displayImage(Context context,String url, ImageView view) {
        Glide.with(context).load(url).into(view);
    }
}
