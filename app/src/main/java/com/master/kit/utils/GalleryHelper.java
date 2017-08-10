package com.master.kit.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.IOException;

/**
 * Created by master on 2016/3/29.
 */
public class GalleryHelper {
    int requestCode;
    Activity activity;
    public GalleryHelper(Activity activity,int requestCode){
        this.activity = activity;
        this.requestCode = requestCode;
    }
    public void openGallery() {
        activity.startActivityForResult(createIntent(), requestCode);
    }

    private Intent createIntent() {
        return new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    }
    private void onPictureReturnedFromGallery(Intent data, Callbacks callbacks) {
        try {
            Uri photoUri = data.getData();
            callbacks.onImagePicked(photoUri);
        } catch (Exception e) {
            e.printStackTrace();
            callbacks.onImagePickerError(e);
        }
    }


    /**
     * 处理得到的图片
     * @param requestCode
     * @param resultCode
     * @param callbacks
     */
    public void handleActivityResult(int requestCode, int resultCode, Intent data, Callbacks callbacks){
        if(requestCode == this.requestCode){
            if(resultCode == Activity.RESULT_OK){
                onPictureReturnedFromGallery(data,callbacks);
            }else{
                callbacks.onCanceled();
            }
        }

    }

/*
    public static Bitmap getCutedBitmapFromIntent(Intent intent) {
        return (Bitmap) intent.getExtras().get("data");
    }*/

    public interface Callbacks{
        void onImagePicked(Uri uri) throws IOException;
        void  onImagePickerError(Exception e);
        void onCanceled();
    }
}
