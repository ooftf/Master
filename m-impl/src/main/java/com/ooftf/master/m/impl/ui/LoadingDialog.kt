package com.ooftf.master.m.impl.ui

import android.content.Context
import android.os.Bundle
import android.view.Window
import com.ooftf.mapping.lib.HttpUiMapping
import com.ooftf.master.m.impl.R
import com.ooftf.master.widget.dialog.ui.BaseDialog
import kotlinx.android.synthetic.main.base_dialog_loading.*

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/7/24 0024
 */
class LoadingDialog : BaseDialog, HttpUiMapping.MyDialogInterface {
    constructor(context: Context) : super(context)

    init {
        setContentView(R.layout.base_dialog_loading)
        setCanceledOnTouchOutside(false)
    }

    override fun getWindow(): Window {
        return super.getWindow()!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loading.indeterminateDrawable = WhiteProgressDrawable()
    }
}