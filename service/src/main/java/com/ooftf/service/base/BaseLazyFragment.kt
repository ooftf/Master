package com.ooftf.service.base

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.components.SimpleImmersionOwner
import com.gyf.immersionbar.components.SimpleImmersionProxy
import com.ooftf.service.R
import com.ooftf.service.engine.LazyFragmentProxy


/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/11/8 0008
 */
abstract class BaseLazyFragment : BaseFragment(), LazyFragmentProxy.LazyFragmentOwner, SimpleImmersionOwner {
    private val mSimpleImmersionProxy = SimpleImmersionProxy(this)
    private val lazyFragmentProxy = LazyFragmentProxy<BaseLazyFragment>(this)

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return lazyFragmentProxy.onCreateView(inflater, container, savedInstanceState)
    }

    @CallSuper
    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lazyFragmentProxy.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mSimpleImmersionProxy.onActivityCreated(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mSimpleImmersionProxy.onDestroy()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        mSimpleImmersionProxy.onHiddenChanged(hidden)
        lazyFragmentProxy.onHiddenChanged()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mSimpleImmersionProxy.onConfigurationChanged(newConfig)
    }

    /**
     * 这个时候view已经创建
     */
    abstract override fun onLoad(rootView: View)

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        lazyFragmentProxy.setUserVisibleHint(isVisibleToUser)
        mSimpleImmersionProxy.isUserVisibleHint = isVisibleToUser
    }

    abstract fun getLayoutId(): Int
    override fun getContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun lazyEnabled(): Boolean {
        return true
    }


    /**
     * 是否可以实现沉浸式，当为true的时候才可以执行initImmersionBar方法
     * Immersion bar enabled boolean.
     *
     * @return the boolean
     */
    override fun immersionBarEnabled(): Boolean {
        return true
    }

    override fun initImmersionBar() {

        val immersionBar = ImmersionBar.with(this).statusBarDarkFont(isDarkFont()).keyboardEnable(true)
        var toolbar = getToolbar()
        if (toolbar == null) {
            toolbar = view?.findViewById<View>(getToolbarId())
        }
        if (toolbar != null) {
            immersionBar.titleBar(toolbar)
        }
        immersionBar.navigationBarColorInt(Color.WHITE).init()
    }

    open fun getToolbarId(): Int {
        return R.id.toolbar
    }

    open fun getToolbar(): View? {
        return null
    }

    open fun isDarkFont(): Boolean {
        return false
    }


    override fun onResume() {
        super.onResume()
        lazyFragmentProxy.onResume()
    }

    override fun onDetach() {
        super.onDetach()
        lazyFragmentProxy.onDetach()
    }


}