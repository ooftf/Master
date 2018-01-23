package com.master.kit.net.mob

/**
 * Created by 99474 on 2018/1/23 0023.
 */

class IdCardQueryBean {

    /**
     * msg : success
     * result : {"area":"广西壮族自治区百色市靖西县","birthday":"1980年04月11日","sex":"女"}
     * retCode : 200
     */

    var msg: String? = null
    var result: ResultBean? = null
    var retCode: String? = null

    class ResultBean {
        /**
         * area : 广西壮族自治区百色市靖西县
         * birthday : 1980年04月11日
         * sex : 女
         */

        var area: String? = null
        var birthday: String? = null
        var sex: String? = null
    }
}
