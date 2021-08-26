package com.ooftf.master.m.impl.ui
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.ooftf.mapping.lib.HttpUiMapping
import com.ooftf.master.m.impl.R
import com.ooftf.master.m.impl.databinding.BaseDialogLoadingBinding
import com.ooftf.master.widget.dialog.ui.BaseDialog
import com.ooftf.master.widget.dialog.ui.getContentView

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/7/24 0024
 */
class LoadingDialog : BaseDialog, HttpUiMapping.MyDialogInterface {
    constructor(context: Context) : super(context)
    var binding: BaseDialogLoadingBinding
    init {
        setContentView(R.layout.base_dialog_loading)
        binding = BaseDialogLoadingBinding.bind(getContentView()!!)
        setCanceledOnTouchOutside(false)
    }

    override fun getWindow(): Window {
        return super.getWindow()!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.loading.indeterminateDrawable = WhiteProgressDrawable()
    }
}