package com.ooftf.kit.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by master on 2016/2/1.
 */
public class IOUtil {
    /**
     * 将字节数据存储到制定文件夹中
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
            // TODO: handle exception
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }

    }
}
