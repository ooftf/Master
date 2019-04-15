package com.ooftf.service.utils

import android.widget.Toast
import com.ooftf.service.base.BaseApplication
import io.reactivex.Observable

fun toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(BaseApplication.instance, message, duration).show()
}
