package com.master.kit.testcase.retrofit

import android.support.v7.app.AlertDialog
import com.dks.master.masterretrofit.BaseBean
import com.dks.master.masterretrofit.ResponseDialog
import com.master.kit.testcase.MainActivity
import tf.oof.com.service.base.BaseActivity

/**
 * Created by master on 2017/10/12 0012.
 */
class EResponseDialog(activity: BaseActivity): ResponseDialog(activity),IEResponse {
    override fun onResponseSuccess(bean: BaseBean) {

    }

    override fun onResponseFailOffSiteLogin(bean: BaseBean) {
        showToMainDialog(bean)
    }

    override fun onResponseFailSessionOverdue(bean: BaseBean) {
        showToMainDialog(bean)
    }

    override fun onResponseFailMessage(bean: BaseBean) {
        AlertDialog
                .Builder(activity)
                .setMessage(bean.info)
                .setPositiveButton("确定", { dialog, which -> dialog.dismiss() })
                .show()
    }

    private fun showToMainDialog(bean: BaseBean) {
        AlertDialog
                .Builder(activity)
                .setMessage(bean.info)
                .setNeutralButton("返回首页", { dialog, which -> activity.startActivity(MainActivity.getStartIntent(activity)) })
                .setCancelable(false)
                .show()
    }

}