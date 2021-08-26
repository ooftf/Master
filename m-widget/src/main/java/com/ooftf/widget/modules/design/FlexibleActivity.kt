package com.ooftf.widget.modules.design

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.widget.R
import com.ooftf.widget.databinding.ActivityFlexibleBinding

/**
 * @author 99474
 */
class FlexibleActivity : BaseViewBindingActivity<ActivityFlexibleBinding>() {

    var fabIsShow = true

    var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
        addFabAnimate()
    }

    private fun addFabAnimate() {
        binding.mainAppbar!!.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout: AppBarLayout, verticalOffset: Int ->
            val percentage = -verticalOffset * 100f / appBarLayout.totalScrollRange
            if (fabIsShow && percentage > 50) {
                fabIsShow = false
                binding.fab!!.animate().scaleX(0f).scaleY(0f).setDuration(200).start()
            }
            if (!fabIsShow && percentage < 50) {
                fabIsShow = true
                binding.fab!!.animate().scaleX(1f).scaleY(1f).setDuration(200).start()
            }
        })
    }

    private fun initToolbar() {
        //  toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        // toolbar.setTitle("臣服吧，你们这些小标题");
        toolbar!!.setNavigationOnClickListener { v: View? -> onBackPressed() }
        toolbar!!.navigationContentDescription = "badk"
    }
}