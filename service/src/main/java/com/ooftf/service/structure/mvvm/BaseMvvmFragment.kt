package com.ooftf.service.structure.mvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ooftf.service.BR
import com.ooftf.service.base.BaseLazyFragment
import com.ooftf.service.structure.mvvm.ui.BaseLiveDataObserve
import java.lang.reflect.ParameterizedType

/**
 * 需要对继承ViewDataBinding进行keep
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/10/9
 */
class BaseMvvmFragment<B : ViewDataBinding, V : BaseViewModel> : BaseLazyFragment() {
    lateinit var binding: B
    lateinit var viewModel: V
    lateinit var baseLiveDataObserve: BaseLiveDataObserve
    override fun onLoad(rootView: View) {
        viewModel = ViewModelProviders.of(this, getViewModelFactory()).get(getVClass())
        binding.lifecycleOwner = this
        binding.setVariable(getVariableId(), viewModel)
        baseLiveDataObserve = viewModel.baseLiveData.attach(this)
    }

    override fun getLayoutId(): Int {
        return 0
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

    open fun getVariableId() = BR.viewModel
    /**
     * 如果报异常代表泛型设置有问题
     */
    private fun getBClass(): Class<B> {
        val superClass = this.javaClass.genericSuperclass
        val pt = superClass as ParameterizedType
        return pt.actualTypeArguments[0] as Class<B>
    }

    override fun getContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        val bClass = getBClass()
        val method = bClass.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
        binding = method.invoke(null, inflater, container, false) as B
        return binding.root
    }
}