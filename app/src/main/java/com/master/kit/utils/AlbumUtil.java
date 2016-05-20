package com.master.kit.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by master on 2016/3/29.
 */
public class AlbumUtil {
    public static void selectCutedPhoto(Activity activity,int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image*//*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 80);
        intent.putExtra("outputY", 80);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, requestCode);
    }
    public  static void selectPhoto(Activity activity,int requestCode){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 配合selectPhoto使用
     * @param context
     * @param intent
     * @return
     */
    public static Bitmap getBitmapFromIntent(Context context,Intent intent) {
        try {
            return (Bitmap) readBitmapFromUri(intent.getData(),400*800,context);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 配合selectCutedPhoto使用
     * @param intent
     * @return
     */
    public static Bitmap getCutedBitmapFromIntent(Intent intent) {
        return (Bitmap) intent.getExtras().get("data");
    }
    private static Bitmap readBitmapFromUri(Uri uri, long requestPixels,
                                           Context context) throws IOException {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        InputStream is = context.getContentResolver().openInputStream(uri);
        BitmapFactory.decodeStream(is, null, options);
        is.close();
        if (options.mCancel || options.outWidth == -1
                || options.outHeight == -1) {
            return null;
        }
        long picSize = options.outHeight * options.outWidth;
        LogUtil.e("outHeight",options.outHeight+"");
        LogUtil.e("outWidth",options.outWidth+"");
        options.inSampleSize = (int) Math.sqrt(picSize / requestPixels);
        if (options.inSampleSize <= 1) {
            options.inSampleSize = 1;
        }
        LogUtil.e("inSampleSize",options.inSampleSize+"");
        options.inJustDecodeBounds = false;
        is = context.getContentResolver().openInputStream(uri);
        Bitmap resultBitmap = BitmapFactory.decodeStream(is, null, options);
        is.close();
        return resultBitmap;
    }

}
