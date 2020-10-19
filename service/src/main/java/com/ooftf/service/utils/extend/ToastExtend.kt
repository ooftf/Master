package com.ooftf.service.utils.extend

import android.view.Gravity
import android.widget.Toast
import com.ooftf.service.base.BaseApplication

fun toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(BaseApplication.instance, message, duration).show()
}

fun toast(message: CharSequence) {
    var toast = Toast.makeText(BaseApplication.instance, message, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}

fun toastCenter(message: CharSequence) {
    var toast = Toast.makeText(BaseApplication.instance, message, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}
