package tf.ooftf.com.service.engine.inputfilter

import android.text.InputFilter
import android.text.Spanned

/**
 * Created by 99474 on 2018/1/12 0012.
 */
class IdCardNumInputFilter : InputFilter {
    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence {
        val result = StringBuilder()
        for (i in 0 until source.length) {
            if (source.get(i).toString().matches(Regex("[0-9Xx]")))
                result.append(source.get(i))
        }
        return result.toString()
    }
}