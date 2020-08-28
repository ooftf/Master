package com.ooftf.master.other.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.other.R
import com.ooftf.service.base.BaseActivity
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.net.etd.bean.BaseBean
import kotlinx.android.synthetic.main.activity_reflect_performance_test.*
import java.lang.reflect.InvocationTargetException

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/3/3 0003
 */
@Route(path = RouterPath.OTHER_ACTIVITY_REFLECT_PERFORMANCE_TEST)
class ReflectPerformanceTestActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reflect_performance_test)
        reflect_button!!.setOnClickListener { v: View? ->
            val start = System.currentTimeMillis()
            for (i in 0 until timers) {
                try {
                    val constructor = BaseBean::class.java.getConstructor()
                    val baseBean = constructor.newInstance()
                } catch (e: NoSuchMethodException) {
                    e.printStackTrace()
                } catch (e: IllegalAccessException) {
                    e.printStackTrace()
                } catch (e: InstantiationException) {
                    e.printStackTrace()
                } catch (e: InvocationTargetException) {
                    e.printStackTrace()
                }
            }
            reflect_text!!.text = (System.currentTimeMillis() - start).toString()
        }
        new_button!!.setOnClickListener { v: View? ->
            val start = System.currentTimeMillis()
            for (i in 0 until timers) {
                val baseBean = BaseBean()
            }
            new_text!!.text = (System.currentTimeMillis() - start).toString()
        }
    }

    val timers: Int
        get() = Integer.valueOf(edit!!.text.toString())
}