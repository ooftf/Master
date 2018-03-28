package com.ooftf.applet.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.applet.R
import com.ooftf.applet.adapter.JXAdapter
import com.ooftf.applet.bean.JXBean
import com.ooftf.service.base.BaseActivity
import kotlinx.android.synthetic.main.activity_jx3.*

@Route(path = "/applet/JX3")
class JX3Activity : BaseActivity() {
    lateinit var adapter: JXAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jx3)
        result.setOnClickListener {
            adapter.add(JXBean(getBasic(), getDamage(), getOther()))
            adapter.notifyDataSetChanged()
        }
        recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = JXAdapter(LayoutInflater.from(this))
        recycler_view.adapter = adapter
    }

    fun getBasic() = if (basic.text.isEmpty()) {
        0
    } else {
        basic.text.toString().toInt()
    }

    fun getDamage() = if (damage.text.isEmpty()) {
        0
    } else {
        damage.text.toString().toInt()
    }

    fun getOther(): MutableList<Int> = if (other.text.isEmpty()) {
        ArrayList()
    } else {
        var result = ArrayList<Int>()
        other.text.toString().split("+").forEach { result.add(it.toInt()) }
        result
    }

}
