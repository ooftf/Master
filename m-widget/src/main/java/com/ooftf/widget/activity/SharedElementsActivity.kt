package com.ooftf.widget.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.widget.R
import com.ooftf.widget.databinding.ActivitySharedElementsBinding

@Route(path = "/widget/activity/sharedElements")
class SharedElementsActivity : BaseViewBindingActivity<ActivitySharedElementsBinding>() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.next.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val i = Intent(this@SharedElementsActivity, SharedElementsSecondaryActivity::class.java)
                val transitionName = getString(R.string.shared_element_icon)
                val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this@SharedElementsActivity, binding.sharedView, transitionName)

                startActivity(i, transitionActivityOptions.toBundle())
            } else {
                startActivity(SharedElementsSecondaryActivity::class.java)
            }

        }
    }
}
