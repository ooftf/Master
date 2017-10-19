package ooftf.com.widget.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pin_entry.*

import ooftf.com.widget.R
import ooftf.com.widget.R.id.toolbar
import tf.oof.com.service.base.BaseSlidingActivity

class PinEntryActivity : BaseSlidingActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin_entry)
    }
}
