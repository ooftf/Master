package com.master.kit.testcase.retrofit

import com.master.kit.bean.BaseBean
import com.ooftf.hi.view.ResponseViewInterface

/**
 * Created by master on 2017/10/12 0012.
 */
interface IEResponse<in T : BaseBean> : ResponseViewInterface {
    fun onResponseSuccess(bean:T)
    fun onResponseFailOffSiteLogin(bean:T)
    fun onResponseFailSessionOverdue(bean:T)
    fun onResponseFailMessage(bean:T)
}