package com.ooftf.master.other.engine;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.core.content.FileProvider;

import java.io.File;

/**
 * @author ooftf
 * @data 2018/8/2
 * @desc 启动系统相机的辅助类
 **/
public class HiCamera {
    private Activity activity;
    private String authority;
    private File file;

    private HiCamera(Activity activity) {
        this.activity = activity;
    }

    public static HiCamera from(Activity activity) {
        return new HiCamera(activity);
    }

    public HiCamera setFileProviderAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    public HiCamera forResult(int requestCode) {
        activity.startActivityForResult(createCameraIntent(), requestCode);
        return this;
    }

    private Intent createCameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, createSavePhotoUri());
        //添加权限
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        return intent;
    }

    public File getResultFile() {
        return file;
    }

    //ProviderConstant.FILE_PROVIDER
    private Uri createSavePhotoUri() {
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/" + activity.getPackageName() + "/" + System.currentTimeMillis() + ".jpg");
        file.getParentFile().mkdirs();
        return FileProvider.getUriForFile(activity, authority, file);
    }
}
