package com.master.kit.testcase.retrofit

import com.dks.master.masterretrofit.BaseBean
import com.dks.master.masterretrofit.View.ResponseView

/**
 * Created by master on 2017/10/12 0012.
 */
interface IEResponse<in T : BaseBean> : ResponseView {
    fun onResponseSuccess(bean:T)
    fun onResponseFailOffSiteLogin(bean:T)
    fun onResponseFailSessionOverdue(bean:T)
    fun onResponseFailMessage(bean:T)
}