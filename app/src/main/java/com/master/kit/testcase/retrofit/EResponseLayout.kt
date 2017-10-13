package com.master.kit.testcase.retrofit

import android.app.Activity
import android.content.Context
import android.support.v7.app.AlertDialog
import android.util.AttributeSet
import com.dks.master.masterretrofit.BaseBean
import com.dks.master.masterretrofit.ResponseLayout
import com.master.kit.testcase.MainActivity
import retrofit2.Response

/**
 * Created by master on 2017/10/12 0012.
 */
class EResponseLayout : ResponseLayout, IEResponse {
    val activity: Activity by lazy {
        if (context is Activity) {
            context as Activity
        } else {
            throw IllegalAccessException("EResponseLayout 的context必须是 Activity")
        }

    }
    override fun onResponseSuccess(bean: BaseBean) {

    }
    override fun onResponseFailOffSiteLogin(bean: BaseBean) {
        showToMainDialog(bean)
    }

    override fun onResponseFailSessionOverdue(bean: BaseBean) {
        showToMainDialog(bean)
    }

    override fun onResponseFailMessage(bean: BaseBean) {
        showToMainDialog(bean)
    }

    private fun showToMainDialog(bean: BaseBean) {
        AlertDialog
                .Builder(activity)
                .setMessage(bean.info)
                .setNeutralButton("返回首页", { dialog, which -> activity.startActivity(MainActivity.getStartIntent(activity)) })
                .show()
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)
}