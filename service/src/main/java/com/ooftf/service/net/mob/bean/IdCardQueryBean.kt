package com.ooftf.service.net.mob.bean

/**
 * Created by 99474 on 2018/1/23 0023.
 */

class IdCardQueryBean: MobBaseBean() {

    /**
     * msg : success
     * result : {"area":"广西壮族自治区百色市靖西县","birthday":"1980年04月11日","sex":"女"}
     * retCode : 200
     */

    var result: ResultBean? = null

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
