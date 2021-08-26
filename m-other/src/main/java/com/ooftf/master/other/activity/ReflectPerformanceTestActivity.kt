package com.ooftf.master.other.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity
import com.ooftf.master.other.databinding.ActivityReflectPerformanceTestBinding
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.net.etd.bean.BaseBean
import java.lang.reflect.InvocationTargetException

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/3/3 0003
 */
@Route(path = RouterPath.OTHER_ACTIVITY_REFLECT_PERFORMANCE_TEST)
class ReflectPerformanceTestActivity : BaseViewBindingActivity<ActivityReflectPerformanceTestBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.reflectButton!!.setOnClickListener { v: View? ->
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
            binding.reflectText!!.text = (System.currentTimeMillis() - start).toString()
        }
        binding.newButton!!.setOnClickListener { v: View? ->
            val start = System.currentTimeMillis()
            for (i in 0 until timers) {
                val baseBean = BaseBean()
            }
            binding.newText!!.text = (System.currentTimeMillis() - start).toString()
        }
    }

    val timers: Int
        get() = Integer.valueOf(binding.edit.text.toString())
}