package com.ooftf.service.structure.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ooftf.service.BR
import com.ooftf.service.structure.mvvm.ui.BaseLiveDataObserve
import com.ooftf.service.base.BaseActivity
import java.lang.reflect.ParameterizedType

/**
 * 需要对继承ViewDataBinding进行keep
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/8/2 0002
 */
abstract class BaseMvvmActivity<B : ViewDataBinding, V : BaseViewModel> : BaseActivity() {
    lateinit var binding: B
    lateinit var viewModel: V
    lateinit var baseLiveDataObserve: BaseLiveDataObserve
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindLayout()
        viewModel = ViewModelProviders.of(this, getViewModelFactory()).get(getVClass())
        binding.lifecycleOwner = this
        binding.setVariable(getVariableId(), viewModel)
        baseLiveDataObserve = viewModel.baseLiveData.attach(this, this)

    }

    open fun getViewModelFactory(): ViewModelProvider.Factory? {
        return null
    }


    /**
     * 如果报异常代表泛型设置有问题
     */
    private fun getVClass(): Class<V> {
        val superClass = this.javaClass.genericSuperclass
        val pt = superClass as ParameterizedType
        return pt.actualTypeArguments[1] as Class<V>
    }

    /**
     * 如果报异常代表泛型设置有问题
     */
    private fun getBClass(): Class<B> {
        val superClass = this.javaClass.genericSuperclass
        val pt = superClass as ParameterizedType
        return pt.actualTypeArguments[0] as Class<B>
    }

    open fun getVariableId() = BR.viewModel

    /**
     * 设置layout，生成binding
     */
    private fun bindLayout() {
        val bClass = getBClass()
        val method = bClass.getMethod("inflate", LayoutInflater::class.java)
        binding = method.invoke(null, layoutInflater) as B
        setContentView(binding.root)
    }
}