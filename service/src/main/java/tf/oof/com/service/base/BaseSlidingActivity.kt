package tf.oof.com.service.base

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import tf.oof.com.service.utils.LogUtil
import tf.oof.com.service.widget.SlidingFrameLayout

import java.util.ArrayList

/**
 * Created by master on 2016/4/1.
 */
open class BaseSlidingActivity : BaseActivity() {
    private lateinit var slidingFrameLayout:SlidingFrameLayout
    override fun setContentView(layoutResID: Int) {
        slidingFrameLayout = SlidingFrameLayout(this)
        val inflate = View.inflate(this, layoutResID, slidingFrameLayout)
        super.setContentView(inflate)
    }
    public fun setEnabled(enabled:Boolean){
        slidingFrameLayout.isEnabled = enabled

    }
}
