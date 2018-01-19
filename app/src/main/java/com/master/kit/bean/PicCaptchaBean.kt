package com.master.kit.bean

/**
 * Created by master on 2017/10/20 0020.
 */
class PicCaptchaBean : BaseBean() {
    var body: Body? = null
    class Body{
        var indentify: String? = null
        var uuid: String? = null
    }
}