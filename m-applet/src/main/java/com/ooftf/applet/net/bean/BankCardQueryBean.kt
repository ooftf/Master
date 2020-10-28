package com.ooftf.applet.net.bean

import com.ooftf.master.session.net.MobBaseBean

/**
 * Created by 99474 on 2018/1/23 0023.
 */

class BankCardQueryBean : MobBaseBean() {

    /**
     * msg : success
     * result : {"bank":"工商银行","bin":"621226","binNumber":6,"cardName":"牡丹卡普卡","cardNumber":19,"cardType":"借记卡"}
     * retCode : 200
     */

    var result: ResultBean? = null

    class ResultBean {
        /**
         * bank : 工商银行
         * bin : 621226
         * binNumber : 6
         * cardName : 牡丹卡普卡
         * cardNumber : 19
         * cardType : 借记卡
         */

        var bank: String? = null
        var bin: String? = null
        var binNumber: Int = 0
        var cardName: String? = null
        var cardNumber: Int = 0
        var cardType: String? = null
    }
}
