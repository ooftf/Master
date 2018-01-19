package tf.oof.com.service.engine.inputfilter

import android.text.InputFilter
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils

/**
 * Created by 99474 on 2018/1/12 0012.
 *
 * 实例Rexgex  [\\u4e00-\\u9fa5|A-Z|a-z|0-9]
 */
class RegexInputFilter(private var pattern:String) : InputFilter {
    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence? {
        var resultString = StringBuilder()
        for (i in start until end) {
            if (Regex(pattern).matches(source[i].toString())) {
                resultString.append(source[i])
            }
        }
        if (source is Spanned) {
            val sp = SpannableString(resultString)
            TextUtils.copySpansFrom(source,
                    start,start+sp.length, null, sp, 0)
            return sp
        } else {
            return resultString
        }
    }
}