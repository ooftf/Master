package com.ooftf.widget.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.service.base.BaseActivity
import com.ooftf.widget.R
import kotlinx.android.synthetic.main.activity_shared_elements.*

@Route(path = "/widget/sharedElements")
class SharedElementsActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_elements)
        next.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                val i = Intent(this@SharedElementsActivity, SharedElementsSecondaryActivity::class.java)
                val transitionName = getString(R.string.shared_element_icon)
                val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this@SharedElementsActivity, sharedView, transitionName)

                startActivity(i, transitionActivityOptions.toBundle())
            }else{
                startActivity(SharedElementsSecondaryActivity::class.java)
            }

        }
    }
}
