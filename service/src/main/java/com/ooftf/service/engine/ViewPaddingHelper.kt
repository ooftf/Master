package com.ooftf.service.engine

import android.view.View

/**
 * Created by 99474 on 2017/12/1 0001.
 */

object ViewPaddingHelper {
    fun setPaddingLeft(view: View, paddingLeft: Int) {
        view.setPadding(paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom)
    }

    fun setPaddingTop(view: View, paddingTop: Int) {
        view.setPadding(view.paddingLeft, paddingTop, view.paddingRight, view.paddingBottom)
    }

    fun setPaddingRight(view: View, paddingRight: Int) {
        view.setPadding(view.paddingLeft, view.paddingTop, paddingRight, view.paddingBottom)
    }

    fun setPaddingBottom(view: View, paddingBottom: Int) {
        view.setPadding(view.paddingLeft, view.paddingTop, view.paddingRight, paddingBottom)
    }
}
