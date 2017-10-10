package com.ooftf.applet.translate

import android.os.Bundle
import com.ooftf.applet.R
import tf.oof.com.service.base.BaseSlidingActivity

/**
 * Created by master on 2017/10/10 0010.
 */
class TranslateActivity: BaseSlidingActivity(){
    val BAI_DU_TRANSLATE_URL = "https://fanyi-api.baidu.com/api/trans/vip/translate"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translate)

    }
}