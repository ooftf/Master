package com.ooftf.service.net.mob

/**
 * Created by 99474 on 2018/1/23 0023.
 */

class BankCardQueryBean {

    /**
     * msg : success
     * result : {"bank":"工商银行","bin":"621226","binNumber":6,"cardName":"牡丹卡普卡","cardNumber":19,"cardType":"借记卡"}
     * retCode : 200
     */

    var msg: String? = null
    var result: ResultBean? = null
    var retCode: String? = null

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
