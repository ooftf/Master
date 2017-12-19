package ooftf.com.widget.self.dialog

import android.app.Activity
import android.app.Dialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ooftf.com.widget.R

/**
 * Created by 99474 on 2017/11/1 0001.
 */
class SelectorDialog(var activity: Activity) : Dialog(activity, R.style.Theme_CustomDialog) {
    var title: TextView
        private set
    var content: TextView
        private set
    var positive: TextView
        private set
    var negative: TextView
        private set
    var line: View
        private set

    init {
        val root = LayoutInflater.from(context).inflate(R.layout.dialog_selector, window.decorView as ViewGroup)
        title = root.findViewById(R.id.title)
        content = root.findViewById(R.id.content)
        positive = root.findViewById(R.id.positive)
        negative = root.findViewById(R.id.negative)
        Log.e("SelectorDialog","::"+content.textSize)
        Log.e("SelectorDialog","::"+positive.textSize)
        line = root.findViewById(R.id.line)
        title.visibility = View.GONE
        content.visibility = View.GONE
        negative.visibility = View.GONE
        line.visibility = View.GONE
        positive.setOnClickListener {
            dismiss();
        }
        negative.setOnClickListener {
            dismiss();
        }
    }

    fun setTitleText(text: CharSequence): SelectorDialog {
        title.visibility = View.VISIBLE
        title.setText(text)
        return this;
    }

    fun setContentText(text: CharSequence): SelectorDialog {
        content.visibility = View.VISIBLE
        content.setText(text)
        return this;
    }

    fun setPositiveText(text: CharSequence): SelectorDialog {
        setPositiveVisibility(View.VISIBLE)
        positive.setText(text)
        return this;
    }

    fun setNegativeText(text: CharSequence): SelectorDialog {
        setNegativeVisibility(View.VISIBLE)
        negative.setText(text)
        return this;
    }

    fun setPositiveListener(listener: (TextView, SelectorDialog) -> Unit): SelectorDialog {
        setPositiveVisibility(View.VISIBLE)
        positive.setOnClickListener {
            listener(positive, this)
        }
        return this;
    }

    fun setNegativeListener(listener: (TextView, SelectorDialog) -> Unit): SelectorDialog {
        setNegativeVisibility(View.VISIBLE)
        negative.setOnClickListener {
            listener(positive, this)
        }
        return this;
    }
    fun setPositiveVisibility(visibility:Int){
        positive.visibility = visibility

    }
    fun setNegativeVisibility(visibility:Int){
        negative.visibility = visibility
    }

    override fun show() {
        if (negative.visibility == View.VISIBLE && positive.visibility == View.VISIBLE) line.visibility = View.VISIBLE
        super.show()
    }
}