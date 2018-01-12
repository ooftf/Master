package tf.oof.com.service.engine.inputfilter

import android.text.InputFilter
import android.text.Spanned
import tf.oof.com.service.utils.RegexUtils

/**
 * Created by 99474 on 2018/1/12 0012.
 */
class ChineseCharactersInputFilter : InputFilter{
    override fun filter(source: CharSequence, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int): CharSequence {
        val result = StringBuilder()
        for (i in 0 until source.length) {
            if (RegexUtils.isZh(source.get(i).toString()))
                result.append(source.get(i))
        }
        return result.toString()
    }
}