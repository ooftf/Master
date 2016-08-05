package com.master.kit.utils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

public class CameraUtil {
    int requestCode;
    Activity activity;
    /**
     * 存放图片的文件名
     */
    String filename = "camera";
    /**
     * 存放拍照图片的url
     */
    Uri savePicUri;
    public CameraUtil(Activity activity, int requestCode) {
        this.requestCode = requestCode;
        this.activity = activity;
    }

    public void openCamera() {
        Intent intent = createCameraIntent();
        activity.startActivityForResult(intent, requestCode);
    }
    /**
     * 创建  开启拍摄的Intent
     * @return
     */
    @NonNull
    private Intent createCameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File cameraPicturesFile = createCameraPicturesFile();
        savePicUri = Uri.fromFile(cameraPicturesFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, savePicUri);
        return intent;
    }

    /**
     * 创建 存放图片的文件
     * @return
     */
    private File createCameraPicturesFile(){
        File file = new File(publicAppExternalDir(), filename);
        if (!file.exists()) file.mkdirs();
        try {
            File imageFile = File.createTempFile(UUID.randomUUID().toString(), ".jpg", file);
            return imageFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * contents are deleted if app is uninstalled.
     *
     * @return
     */
    private String publicAppExternalDir() {
        return activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath();
    }

    /**
     * 处理得到的图片
     * @param requestCode
     * @param resultCode
     * @param callbacks
     */
    public void handleActivityResult(int requestCode, int resultCode, Callbacks callbacks){
        if(requestCode == this.requestCode){
            if(resultCode == Activity.RESULT_OK){
                onPictureReturnedFromCamera(callbacks);
            }else{
                callbacks.onCanceled();
            }
        }

    }
    private void onPictureReturnedFromCamera(Callbacks callbacks){
        try {
            File file = new File(new URI(savePicUri.toString()));
            callbacks.onImagePicked(file);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            callbacks.onImagePickerError(e);
        }
    }

    /**
     * 通知手机相册更新这张照片，如果不需要让系统相册显示，则不需要调用
     */
    public void notifyGallery() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(savePicUri);
        activity.sendBroadcast(mediaScanIntent);
    }
    public interface Callbacks{
        void onImagePicked(File imageFile);
        void  onImagePickerError(Exception e);
        void onCanceled();
    }
}
