package com.master.kit.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by master on 2016/8/5.
 */
public class BitmapUtils {
    public static Bitmap readBitmapFromPath(String filePath, long requestPixels) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        if (options.mCancel || options.outWidth == -1 || options.outHeight == -1) {
            return null;
        }
        long picSize = options.outHeight * options.outWidth;
        options.inSampleSize = (int) Math.sqrt(picSize / requestPixels);
        if (options.inSampleSize <= 1) {
            options.inSampleSize = 1;
        }
        options.inJustDecodeBounds = false;
        Bitmap resultBitmap = BitmapFactory.decodeFile(filePath, options);
        return resultBitmap;
    }
}
