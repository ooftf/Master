package tf.oof.com.service.base

import android.app.Activity
import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView
import tf.oof.com.service.R

/**
 * Created by master on 2017/10/10 0010.
 */
class TailoredToolbar :Toolbar {
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        setNavigationOnClickListener { (context as Activity).finish() }
        setBackgroundColor(resources.getColor(R.color.colorPrimary))
    }

    override fun setTitle(title: CharSequence?) {
        super.setTitle(title)
        post {
            var title: TextView? = null
            (0..childCount)
                    .filter { getChildAt(it) is TextView }
                    .map { getChildAt(it) as TextView }
                    .filter {
                        Log.e("text", it.text.toString())
                        it.text == getTitle()
                    }
                    .forEach { title = it }
            title?.let {
                it.offsetLeftAndRight((width - it.width) / 2 - it.left)
            }
        }
    }
}