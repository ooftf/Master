package com.ooftf.master.m.impl.net

import com.ooftf.log.JLog
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2021/8/27
 */
@Singleton
class LogInterceptor @Inject constructor() : HttpLoggingInterceptor.Logger {
    val responseEndTag = "<-- END HTTP"
    val requestEndTag = "--> END"
    val threadLocal = ThreadLocal<StringBuilder>()
    override fun  log(message: String) {
        var sb = threadLocal.get()
        if (sb == null) {
            sb = StringBuilder()
            threadLocal.set(sb)
        }
        if (message.startsWith("{") && message.endsWith("}")||message.startsWith("[") && message.endsWith("]")) {
            sb.appendLine(jsonToString(message))
        } else if (message.startsWith(requestEndTag)) {
            sb.appendLine(message).appendLine().appendLine()
        } else if (message.startsWith(responseEndTag)) {
            sb.append(message)
            synchronized(this){
                JLog.dJson("http", sb.toString())
            }
            sb.clear()
        } else {
            sb.appendLine(message)
        }
    }

    fun jsonToString(string: String): String {
        var message = string
        try {
            if (message.startsWith("{")) {
                val jsonObject = JSONObject(message)
                //最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
                message = jsonObject.toString(4)
            } else if (message.startsWith("[")) {
                val jsonArray = JSONArray(message)
                message = jsonArray.toString(4)
            }
        } catch (ignored: JSONException) {
            ignored.printStackTrace()
        }
        return message
    }
}