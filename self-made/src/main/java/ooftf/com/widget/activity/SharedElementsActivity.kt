package ooftf.com.widget.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_shared_elements.*
import ooftf.com.widget.R
import tf.oof.com.service.base.BaseActivity


class SharedElementsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_elements)
        next.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                val i = Intent(this@SharedElementsActivity, SharedElementsSecondaryActivity::class.java)
                val transitionName = getString(R.string.app_name)

                val transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this@SharedElementsActivity, sharedView, transitionName)

                startActivity(i, transitionActivityOptions.toBundle())
            }else{
                startActivity(SharedElementsSecondaryActivity::class.java)
            }

        }
    }
}
