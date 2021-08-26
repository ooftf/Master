package com.ooftf.master.other.activity

import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.other.engine.GlideEngineV471
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.master.other.databinding.ActivityMatissePhotoBinding
import com.ooftf.service.constant.ProviderConstant
import com.tbruyelle.rxpermissions3.RxPermissions
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.internal.entity.CaptureStrategy


/**
 * https://github.com/zhihu/Matisse
 */
@Route(path = "/other/activity/matisse")
class MatissePhotoActivity : BaseViewBindingActivity<ActivityMatissePhotoBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.button.setOnClickListener {
            takePhoto();
        }
    }

    fun takePhoto() {
        RxPermissions(this)
                .request(Manifest.permission.CAMERA)
                .subscribe {
                    if (it) {
                        Matisse.from(this)
                                .choose(MimeType.ofAll())
                                .countable(true)
                                .maxSelectable(9)
                                .capture(true)
                                .captureStrategy(CaptureStrategy(true, ProviderConstant.FILE_PROVIDER))
                                .spanCount(3)
                                //.addFilter(MiniSizeFilter(320,320,5 * Filter.K * Filter.K))
                                //.addFilter(GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                //.gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.layout_matisse_expected_size))//期望每个item占用的大小
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                                .thumbnailScale(0.85f)
                                .imageEngine(GlideEngineV471())
                                .forResult(REQUEST_CODE_CHOOSE)
                    } else {
                        toast("权限被拒绝")
                    }
                }
    }

    var mSelected: List<Uri>? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            Log.e("mSelected", mSelected.toString())
        }
    }

    companion object {
        val REQUEST_CODE_CHOOSE = 256
    }
}
