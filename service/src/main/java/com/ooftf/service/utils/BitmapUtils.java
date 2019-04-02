package com.ooftf.service.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Log;

import com.nostra13.universalimageloader.utils.IoUtils;

import androidx.annotation.NonNull;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

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

    /**
     * 高斯模糊
     *
     * @param context
     * @param source
     * @param radius
     * @return
     */
    public static Bitmap rsBlur(Context context, Bitmap source, int radius) {

        Bitmap inputBmp = source;
        //(1)
        RenderScript renderScript = RenderScript.create(context);

        JLog.i("scale size:" + inputBmp.getWidth() + "*" + inputBmp.getHeight());

        // Allocate memory for Renderscript to work with
        //(2)
        final Allocation input = Allocation.createFromBitmap(renderScript, inputBmp);
        final Allocation output = Allocation.createTyped(renderScript, input.getType());
        //(3)
        // Load up an instance of the specific script that we want to use.
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        //(4)
        scriptIntrinsicBlur.setInput(input);
        //(5)
        // Set the blur radius
        scriptIntrinsicBlur.setRadius(radius);
        //(6)
        // Start the ScriptIntrinisicBlur
        scriptIntrinsicBlur.forEach(output);
        //(7)
        // Copy the output to the blurred bitmap
        output.copyTo(inputBmp);
        //(8)
        renderScript.destroy();

        return inputBmp;
    }

    /**
     * 将图片地址转换成图片字节
     * 策略：
     * 如果图片本身不超过maxByte，直接读文件生成字节
     * 如果图片本身超过maxByte，则将图片以不超过maxPixel的方式读取到内存中，然后以Bitmap.compress的形式将图片压缩到maxByte以下
     *
     * @param srcPath
     * @param maxByte
     * @param maxByte
     * @return
     */
    public static Single<byte[]> rxGetImage(final String srcPath, final int maxPixel, final int maxByte) {
        return rxReadFile(srcPath, maxByte)
                .onErrorResumeNext((Function<Throwable, SingleSource<byte[]>>) throwable ->
                        rxGetImage(srcPath, maxPixel)
                                .flatMap((Function<Bitmap, SingleSource<byte[]>>) bitmap -> compressImage(bitmap, maxByte)));

    }

    /**
     * 读取文件，如果文件大于maxByte则走异常流
     *
     * @param srcPath
     * @param maxByte
     * @return
     */
    public static Single<byte[]> rxReadFile(final String srcPath, final int maxByte) {
        return Single.create(emitter -> {
            File file = new File(srcPath);
            if (file.length() <= maxByte && getExifDegree(srcPath) == 0) {
                byte[] bytes = new byte[(int) file.length()];
                InputStream is = new FileInputStream(file);
                is.read(bytes);
                IoUtils.closeSilently(is);
                emitter.onSuccess(bytes);

            } else {
                emitter.onError(new Exception("file > maxByte"));
            }
        });
    }

    /**
     * 获取到图片的Exif角度
     *
     * @param path
     * @return
     */
    public static int getExifDegree(String path) {
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            int degree = 0;
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
            Log.e("degree", "::" + degree);
            return degree;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 以最大不超过maxPixel的条件将图片加载到内存中
     *
     * @param srcPath
     * @param maxPixel
     * @return
     */
    public static Single<Bitmap> rxGetImage(final String srcPath, final int maxPixel) {
        return Single.create((SingleOnSubscribe<Bitmap>) emitter -> {
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
            newOpts.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
            newOpts.inJustDecodeBounds = false;
            float w = newOpts.outWidth;
            float h = newOpts.outHeight;
            // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
            int be = 1;// be=1表示不缩放
            if (w * h > maxPixel) {
                be = (int) Math.round(Math.sqrt(w * h / maxPixel));
            }
            if (be < 1) {
                be = 1;
            }
            newOpts.inSampleSize = be;// 设置缩放比例
            // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
            Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
            emitter.onSuccess(bitmap);
        })
                //如果Exif中包含的旋转角度信息不为0，则旋转图片
                .flatMap((Function<Bitmap, SingleSource<Bitmap>>) bitmap -> getRotateBitmap(bitmap, getExifDegree(srcPath)));
    }

    /**
     * 获取旋转指定角度度的图片
     *
     * @param bitmap
     * @return
     */
    private static Single<Bitmap> getRotateBitmap(final Bitmap bitmap, final int degree) {
        return Single.fromCallable(() -> {
            if (degree % 360 == 0) {
                return bitmap;
            }
            Matrix m = new Matrix();
            m.postRotate(degree);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
        });
    }

    /**
     * 将bitmap转换为字节
     *
     * @param image
     * @param maxBytes
     * @return
     */
    public static Single<byte[]> compressImage(final Bitmap image, final int maxBytes) {
        if (image == null) {
            return Single.error(new Exception("Bitmap == null"));
        }
        return Single.create(emitter -> {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int options = 100;
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            int baseIncrement = 2;
            int increment = (baos.size() / maxBytes) * baseIncrement;//计算压缩增量
            if (increment < baseIncrement) {
                increment = baseIncrement;
            }
            if (increment > 10) {
                increment = 10;
            }
            while (baos.toByteArray().length > maxBytes) { // 循环判断如果压缩后图片是否大于1M,大于继续压缩
                options -= increment;// 每次都减少
                baos.reset();// 重置baos即清空baos
                image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            }
            emitter.onSuccess(baos.toByteArray());
        });
    }
}
