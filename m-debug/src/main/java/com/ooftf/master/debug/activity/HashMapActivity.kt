package com.ooftf.master.debug.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.debug.R
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import com.ooftf.service.utils.extend.toast
import kotlinx.android.synthetic.main.activity_hash_map.*

@Route(path = "/debug/activity/hashMap")
class HashMapActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hash_map)
        var map = HashMap<CustomObject, String>()
        map[CustomObject("aa", 15)] = "15"
        map[CustomObject2("aa", 15)] = "15"
        button.setOnClickListener {
            toast("只重写了equals方法：${map[CustomObject("aa", 15)]}," +
                    "\n 重写了equals和hashcode：${map[CustomObject2("aa", 15)]} ")
        }
    }

    open class CustomObject(var name: String = "", var age: Int = 5) {
        override fun equals(other: Any?): Boolean {
            if (other !is CustomObject) {
                return false
            }
            return other.name == name && other.age == age
        }
    }

    class CustomObject2(name: String = "", age: Int = 5) : CustomObject(name, age) {
        override fun hashCode(): Int {
            var result = 17
            result = result * 31 + name.hashCode()
            result = result * 31 + age
            return result
        }
    }
}
