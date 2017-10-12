package com.master.kit.testcase.retrofit

import com.dks.master.masterretrofit.BaseBean
import com.dks.master.masterretrofit.IViewResponse

/**
 * Created by master on 2017/10/12 0012.
 */
interface IEResponse:IViewResponse {
    fun onResponseSuccess(bean:BaseBean)
    fun onResponseFailOffSiteLogin(bean:BaseBean)
    fun onResponseFailSessionOverdue(bean:BaseBean)
    fun onResponseFailMessage(bean:BaseBean)
}