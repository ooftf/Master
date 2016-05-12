package com.dks.master.decoupling;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by master on 2016/5/10.
 */
public class ImageLoaderWapper {
    void display(String url, ImageView view){

    }
    void display(String url, ImageView view,Config config){

    }
    void display(int resId, ImageView view){

    }

    void display(int resId, ImageView view,Config config){

    }

    public static class Config{
        int width;
        int height;
        int error;
        int loading;
        private boolean cacheInMemory = true;
        private boolean cacheOnDisk = true;
    }
}
