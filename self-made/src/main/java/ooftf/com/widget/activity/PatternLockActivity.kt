package ooftf.com.widget.activity

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pin_entry.*
import ooftf.com.widget.R
import tf.oof.com.service.base.BaseSlidingActivity

class PatternLockActivity : BaseSlidingActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pattern_lock)
    }
}
