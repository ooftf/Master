package com.ooftf.service.widget.toolbar

import android.app.Activity
import android.content.Context
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import com.ooftf.service.R

/**
 * Created by master on 2017/10/10 0010.
 */
open class TailoredToolbar : Toolbar {


    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        setNavigationOnClickListener { (context as Activity).finish() }
        setBackgroundColor(resources.getColor(R.color.colorPrimary))
        //LayoutInflater.from(context).inflate(R.layout.toolbar_title_center,this)
    }

}