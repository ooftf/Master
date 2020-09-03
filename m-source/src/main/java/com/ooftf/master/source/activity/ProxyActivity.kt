package com.ooftf.master.source.activity

import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.master.source.R
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_proxy.*
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

@Route(path = "/source/activity/proxy")
class ProxyActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proxy)
        button.setOnClickListener {
            var impl = OneImpl()
            val proxyImpl = OneImplDynamicProxy(impl).newProxyInstance() as OneInterface
            impl.doSomething()
            proxyImpl.doSomething()
        }
    }

    interface OneInterface {
        fun doSomething()
    }

    class OneImpl : OneInterface {
        override fun doSomething() {
            Log.e("proxy", "doSomething")
        }
    }

    class OneImplProxy : OneInterface {
        var impl = OneImpl()
        override fun doSomething() {
            Log.e("proxy", "doBefore")
            impl.doSomething()
            Log.e("proxy", "doAfter")
        }
    }

    class OneImplDynamicProxy(private var target: Any) : InvocationHandler {
        fun newProxyInstance(): Any =
                Proxy.newProxyInstance(target.javaClass.classLoader, target.javaClass.interfaces, this)

        override fun invoke(proxy: Any?, method: Method, args: Array<out Any>?): Any? {
            Log.e("DynamicProxy", "${method.name} before")
            val result = if (args == null) {
                method.invoke(target)
            } else {
                method.invoke(target, *args)
            }
            Log.e("DynamicProxy", "${method.name} after")
            return result
        }
    }

}
