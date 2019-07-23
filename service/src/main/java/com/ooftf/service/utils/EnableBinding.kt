package com.ooftf.service.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/7/23 0023
 */
object EnableBinding {
    fun binding(view: View, vararg textViews: TextView) {
        judgeEnable(textViews, view)
        textViews.forEach {
            it.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    judgeEnable(textViews, view)
                }

            })
        }
    }

    private fun judgeEnable(textViews: Array<out TextView>, view: View) {
        val firstOrNull = textViews.firstOrNull { textView ->
            textView.text.isEmpty()
        }
        view.isEnabled = firstOrNull == null
    }
}