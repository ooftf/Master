package com.master.kit.net.etd

import com.master.kit.bean.BaseBean
import com.ooftf.hi.view.HiResponseView
/**
 * Created by master on 2017/10/12 0012.
 */
interface ResponseView<in T : BaseBean> : HiResponseView<T> {
    fun onResponseSuccess(bean:T)
    fun onResponseFailOffSiteLogin(bean:T)
    fun onResponseFailSessionOverdue(bean:T)
    fun onResponseFailMessage(bean:T)
}