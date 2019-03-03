package com.ooftf.master.other.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.other.R
import com.ooftf.master.other.engine.HiCamera
import com.ooftf.service.base.BaseActivity
import com.ooftf.service.constant.ProviderConstant
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_camera.*


@Route(path = "/other/activity/camera")
class CameraActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        button.setOnClickListener {
            checkPermissionsToCamera();
        }
    }

    private fun checkPermissionsToCamera() {
        RxPermissions(this)
                .request(Manifest.permission.CAMERA)
                .subscribe {
                    if (it) {
                        camera()
                    } else {
                        toast("获取相机权限失败")
                    }
                }
    }

    var hiCamera: HiCamera? = null
    private fun camera() {
        hiCamera = HiCamera
                .from(this)
                .setFileProviderAuthority(ProviderConstant.FILE_PROVIDER)
                .forResult(REQUEST_CODE_CAMERA)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            //操作File对象
            Log.e("hiCamera", hiCamera?.resultFile!!.path)
            imageView.setImageBitmap(BitmapFactory.decodeFile(hiCamera!!.resultFile!!.absolutePath))
        }
    }

    companion object {
        val REQUEST_CODE_CAMERA = 456
    }
}