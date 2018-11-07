package com.ooftf.service.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/11/8 0008
 */
abstract class BaseLazyFragment : BaseFragment() {
    private var isLoaded: Boolean = false
    private var contentView: View? = null
    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        if (contentView == null) {
            contentView = inflater.inflate(getContentLayoutId(), container, false)
            isLoaded = false
        }
        return contentView
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadJudgment()
    }

    private fun loadJudgment() {
        if (view != null && userVisibleHint && !isLoaded) {
            isLoaded = true
            onLazyLoad()
        }
    }
    abstract fun onLazyLoad()
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        loadJudgment()
    }

    abstract fun getContentLayoutId(): Int
}