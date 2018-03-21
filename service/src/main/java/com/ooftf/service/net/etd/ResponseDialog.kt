package com.ooftf.service.net.etd

import android.app.Activity
import android.support.v7.app.AlertDialog
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.hihttp.view.HiResponseDialog
import com.ooftf.service.net.etd.bean.BaseBean

/**
 * Created by master on 2017/10/12 0012.
 */
class ResponseDialog(activity: Activity, text: String = "加载中") : HiResponseDialog<BaseBean>(activity, text), ResponseView<BaseBean> {

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
                .setPositiveButton("确定", { dialog, _ -> dialog.dismiss() })
                .show()
    }

    private fun showToMainDialog(bean: BaseBean) {
        AlertDialog
                .Builder(activity)
                .setMessage(bean.info)
                .setNeutralButton("返回首页", { _, _ -> ARouter.getInstance().build("/main/main").navigation() })
                .setCancelable(false)
                .show()
    }

}