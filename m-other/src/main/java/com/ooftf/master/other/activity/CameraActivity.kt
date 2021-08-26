package com.ooftf.master.other.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.master.other.R
import com.ooftf.master.other.databinding.ActivityCameraBinding
import com.ooftf.master.other.engine.HiCamera
import com.ooftf.service.constant.ProviderConstant
import com.tbruyelle.rxpermissions3.RxPermissions


@Route(path = "/other/activity/camera")
class CameraActivity : BaseViewBindingActivity<ActivityCameraBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.button.setOnClickListener {
            checkPermissionsToCamera();
        }
    }

    private fun checkPermissionsToCamera() {
        RxPermissions(this)
                .request(Manifest.permission.CAMERA)
                .bindDestroy()
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
            binding.imageView.setImageBitmap(BitmapFactory.decodeFile(hiCamera!!.resultFile!!.absolutePath))
        }
    }

    companion object {
        val REQUEST_CODE_CAMERA = 456
    }
}