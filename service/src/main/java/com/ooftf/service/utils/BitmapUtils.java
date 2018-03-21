package com.ooftf.service.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by master on 2016/8/5.
 */
public class BitmapUtils {
    public static Bitmap readBitmapFromPath(String filePath, long requestPixels) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        if (options.mCancel || options.outWidth == -1 || options.outHeight == -1) {
            return null;
        }

        calculateInSampleSize(requestPixels, options);

        options.inJustDecodeBounds = false;
        Bitmap resultBitmap = BitmapFactory.decodeFile(filePath, options);
        return resultBitmap;
    }

    public static Bitmap readBitmapFromUri(Uri uri, long requestPixels,
                                           Context context) throws IOException {

        BitmapFactory.Options options = getJustDecodeOptions(uri, context);
        if (options.mCancel || options.outWidth == -1
                || options.outHeight == -1) {
            return null;
        }

        calculateInSampleSize(requestPixels, options);

        return getBitmap(uri, context, options);
    }

    /**
     * 根据缩放比获取图片
     */
    private static Bitmap getBitmap(Uri uri, Context context, BitmapFactory.Options options) throws IOException {
        InputStream is;
        options.inJustDecodeBounds = false;
        is = context.getContentResolver().openInputStream(uri);
        Bitmap resultBitmap = BitmapFactory.decodeStream(is, null, options);
        is.close();
        return resultBitmap;
    }

    /**
     * 计算 压缩比
     */
    private static void calculateInSampleSize(long requestPixels, BitmapFactory.Options options) {
        long picSize = options.outHeight * options.outWidth;
        int inSampleSize = (int) java.lang.Math.sqrt(picSize / requestPixels);
        if (inSampleSize <= 1) {
            inSampleSize = 1;
        }
        options.inSampleSize = inSampleSize;
    }

    /**
     * 获取图片的参数
     */
    @NonNull
    private static BitmapFactory.Options getJustDecodeOptions(Uri uri, Context context) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        InputStream is = context.getContentResolver().openInputStream(uri);
        BitmapFactory.decodeStream(is, null, options);
        is.close();
        return options;
    }
}
