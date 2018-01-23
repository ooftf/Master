package com.master.kit.net.etd

import android.support.v7.app.AlertDialog
import com.master.kit.bean.BaseBean
import com.master.kit.activity.MainActivity
import com.ooftf.hi.view.HiResponseDialog
import tf.oof.com.service.base.BaseActivity

/**
 * Created by master on 2017/10/12 0012.
 */
class ResponseDialog(activity: BaseActivity, text:String="加载中"): HiResponseDialog<BaseBean>(activity,text), ResponseView<BaseBean> {

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
                .setNeutralButton("返回首页", { _, _ -> activity.startActivity(MainActivity.getStartIntent(activity)) })
                .setCancelable(false)
                .show()
    }

}