package com.ooftf.service.utils

import java.util.regex.Pattern

/**
 * Created by 99474 on 2018/1/12 0012.
 */

object RegexUtils {
    /**
     * 正则：手机号（简单）
     */
    val REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$"
    /**
     * 正则：手机号（精确）
     *
     * 移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198
     *
     * 联通：130、131、132、145、155、156、166、171、175、176、185、186
     *
     * 电信：133、153、173、177、180、181、189、199
     *
     * 全球星：1349
     *
     * 虚拟运营商：170
     */
    val REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(16[6])|(17[0,1,3,5-8])|(18[0-9])|(19[8,9]))\\d{8}$"
    /**
     * 正则：电话号码
     */
    val REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}"
    /**
     * 正则：身份证号码 15 位
     */
    val REGEX_ID_CARD15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$"
    /**
     * 正则：身份证号码 18 位
     */
    val REGEX_ID_CARD18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$"
    /**
     * 正则：邮箱
     */
    val REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"
    /**
     * 正则：URL
     */
    val REGEX_URL = "[a-zA-z]+://[^\\s]*"
    /**
     * 正则：汉字
     */
    val REGEX_ZH = "^[\\u4e00-\\u9fa5]+$"

    /**
     * 判断是否匹配正则
     *
     * @param regex 正则表达式
     * @param input 要匹配的字符串
     * @return `true`: 匹配<br></br>`false`: 不匹配
     */
    fun isMatch(regex: String, input: CharSequence?): Boolean {
        return input != null && input.length > 0 && Pattern.matches(regex, input)
    }

    /**
     * 验证汉字
     *
     * @param input 待验证文本
     * @return `true`: 匹配<br></br>`false`: 不匹配
     */
    fun isZh(input: CharSequence): Boolean {
        return isMatch(REGEX_ZH, input)
    }

    /**
     * 验证邮箱
     *
     * @param input 待验证文本
     * @return `true`: 匹配<br></br>`false`: 不匹配
     */
    fun isEmail(input: CharSequence): Boolean {
        return isMatch(REGEX_EMAIL, input)
    }

    /**
     * 验证电话号码
     *
     * @param input 待验证文本
     * @return `true`: 匹配<br></br>`false`: 不匹配
     */
    fun isTel(input: CharSequence): Boolean {
        return isMatch(REGEX_TEL, input)
    }

    /**
     * 验证身份证号码 15 位
     *
     * @param input 待验证文本
     * @return `true`: 匹配<br></br>`false`: 不匹配
     */
    fun isIDCard15(input: CharSequence): Boolean {
        return isMatch(REGEX_ID_CARD15, input)
    }

    /**
     * 验证身份证号码 18 位
     *
     * @param input 待验证文本
     * @return `true`: 匹配<br></br>`false`: 不匹配
     */
    fun isIDCard18(input: CharSequence): Boolean {
        return isMatch(REGEX_ID_CARD18, input)
    }
    ///////////////////////////////////////////////////////////////////////////
    // If u want more please visit http://toutiao.com/i6231678548520731137
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 验证手机号（简单）
     *
     * @param input 待验证文本
     * @return `true`: 匹配<br></br>`false`: 不匹配
     */
    fun isMobileSimple(input: CharSequence): Boolean {
        return isMatch(REGEX_MOBILE_SIMPLE, input)
    }

    /**
     * 验证手机号（精确）
     *
     * @param input 待验证文本
     * @return `true`: 匹配<br></br>`false`: 不匹配
     */
    fun isMobileExact(input: CharSequence): Boolean {
        return isMatch(REGEX_MOBILE_EXACT, input)
    }
}
