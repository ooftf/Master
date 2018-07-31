package com.ooftf.master.other.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.other.R
import com.zhihu.matisse.Matisse
import kotlinx.android.synthetic.main.activity_matisse.*
import com.zhihu.matisse.engine.impl.GlideEngine
import android.content.pm.ActivityInfo
import android.net.Uri
import android.util.Log
import com.zhihu.matisse.MimeType


/**
 * https://github.com/zhihu/Matisse
 */
@Route(path = "/other/matisseActivity")
class MatisseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matisse)
        button.setOnClickListener {
            Matisse.from(this)
                    .choose(MimeType.ofAll())
                    .countable(true)
                    .maxSelectable(9)
                    .theme(R.style.Matisse_Zhihu)
                    .gridExpectedSize(25)
                    .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .thumbnailScale(0.85f)
                    .imageEngine(GlideEngine())
                    .forResult(REQUEST_CODE_CHOOSE)
        }
    }

    var mSelected: List<Uri>? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            mSelected = Matisse.obtainResult(data);
            Log.e("mSelected",mSelected.toString())
        }
    }
    companion object {
        val REQUEST_CODE_CHOOSE = 256;
    }
}
