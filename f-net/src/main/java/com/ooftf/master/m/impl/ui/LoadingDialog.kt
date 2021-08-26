package com.ooftf.master.m.impl.ui

import android.content.DialogInterface
import android.view.View
import android.view.Window
import com.ooftf.mapping.lib.HttpUiMapping
import com.ooftf.master.m.impl.databinding.BaseDialogLoadingBinding
import com.ooftf.master.widget.dialog.ui.df.original.BaseBindingDialogFragment

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/7/24 0024
 */
class LoadingDialog : BaseBindingDialogFragment<BaseDialogLoadingBinding>(),
    HttpUiMapping.MyDialogInterface {


    override fun cancel() {
        dismiss()
    }

    override fun getWindow(): Window {
        return dialog!!.window
    }

    var mListener: DialogInterface.OnCancelListener? = null
    override fun setOnCancelListener(listener: DialogInterface.OnCancelListener?) {
        mListener = listener

    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        mListener?.onCancel(this)
    }

    override fun show() {
        show(requireActivity().supportFragmentManager, "LoadingDialog")
    }

    override fun initView(view: View) {
        binding.loading.indeterminateDrawable = WhiteProgressDrawable()
    }

}