package com.ooftf.service.utils.extend

import android.view.Gravity
import android.widget.Toast
import com.ooftf.basic.AppHolder.app

fun toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(app, message, duration).show()
}

fun toast(message: CharSequence) {
    var toast = Toast.makeText(app, message, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}

fun toastCenter(message: CharSequence) {
    var toast = Toast.makeText(app, message, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}
