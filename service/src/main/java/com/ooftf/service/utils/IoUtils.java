package com.ooftf.service.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by master on 2016/2/1.
 */
public class IoUtils {

    /**
     * 32 KB
     */
    public static final int DEFAULT_BUFFER_SIZE = 32 * 1024;

    /**
     * 将字节数据存储到制定文件夹中
     *
     * @param context
     * @param path
     * @param fileName
     * @param data
     * @throws IOException
     */
    public static void saveBinary(Context context, String path, String fileName, byte[] data) throws IOException {

        FileOutputStream fileOutputStream = null;
        try {
            // 判断有没有sdcard
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                // 判断文件夹有没有
                File filePath = new File(path);
                if (!filePath.exists()) {
                    filePath.mkdirs();
                }
                File file = new File(path, fileName);
                if (file.exists()) {
                    file.delete();
                }
                // 写文件
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(data);
            } else {
                Toast.makeText(context, "没有sdcard", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSilently(fileOutputStream);
        }

    }

    public static void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception ignored) {
            }
        }
    }

    public static boolean copyStream(InputStream is, OutputStream os) throws IOException {
        return copyStream(is, os, DEFAULT_BUFFER_SIZE);
    }

    public static boolean copyStream(InputStream is, OutputStream os, int bufferSize)
            throws IOException {
        final byte[] bytes = new byte[bufferSize];
        int count;
        while ((count = is.read(bytes, 0, bufferSize)) != -1) {
            os.write(bytes, 0, count);
        }
        os.flush();
        return true;
    }
}
