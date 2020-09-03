package com.ooftf.service.utils.extend

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/9/30
 */
fun Context.inflate(layoutId: Int, parent: ViewGroup, isAttach: Boolean = false): View {
    return LayoutInflater.from(this).inflate(layoutId, parent, isAttach)
}

fun ViewGroup.inflate(layoutId: Int, isAttach: Boolean = false): View {
    return this.context.inflate(layoutId, this, isAttach)
}