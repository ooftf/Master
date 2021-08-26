package com.ooftf.applet.activity

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.applet.adapter.JXAdapter
import com.ooftf.applet.bean.JXBean
import com.ooftf.applet.databinding.ActivityJx3Binding
import com.ooftf.arch.frame.mvvm.activity.BaseViewBindingActivity

/**
 * 剑网三收益计算器
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/20 0020
 */
@Route(path = "/applet/activity/JX3")
class JX3Activity : BaseViewBindingActivity<ActivityJx3Binding>() {
    lateinit var adapter: JXAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.result.setOnClickListener {
            adapter.add(JXBean(getBasic(), getDamage(), getOther()))
            adapter.notifyDataSetChanged()
        }
        binding.recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adapter = JXAdapter(LayoutInflater.from(this))
        binding.recyclerView.adapter = adapter
    }

    fun getBasic() = if (binding.basic.text.isEmpty()) {
        0
    } else {
        binding.basic.text.toString().toInt()
    }

    fun getDamage() = if (binding.damage.text.isEmpty()) {
        0
    } else {
        binding.damage.text.toString().toInt()
    }

    fun getOther(): MutableList<Int> = if (binding.other.text.isEmpty()) {
        ArrayList()
    } else {
        var result = ArrayList<Int>()
        binding.other.text.toString().split("+").forEach { result.add(it.toInt()) }
        result
    }

}
