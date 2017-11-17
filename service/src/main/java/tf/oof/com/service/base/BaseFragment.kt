package tf.oof.com.service.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import tf.oof.com.service.interfaces.ILifecycle
import tf.oof.com.service.utils.LogUtil
import java.util.*

/**
 * Created by master on 2016/4/12.
 */
open class BaseFragment : Fragment(), ILifecycle {
    override fun postOnDestroy(doOnDestroy: () -> Unit) {
        onDestroyList.add(doOnDestroy)
    }

    override fun isAlive(): Boolean {
        return alive
    }

    override fun isShowing(): Boolean {
        return showing
    }

    override fun isTouchable(): Boolean {
        return touchable
    }

    private var isLoaded: Boolean = false
    var mToast: Toast? = null
    private var touchable = false
    private var showing = false
    private var alive = false
    private var onResumeList: MutableList<() -> Unit> = ArrayList()
    private val onDestroyList: MutableList<() -> Unit> by lazy { ArrayList<() -> Unit>() }
    override fun onAttach(context: Context) {
        alive = true
        LogUtil.e(javaClass.simpleName, "onAttach")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        LogUtil.e(javaClass.simpleName, "onCreate          " + savedInstanceState)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        LogUtil.e(javaClass.simpleName, "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        LogUtil.e(javaClass.simpleName, "onActivityCreated")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        LogUtil.e(javaClass.simpleName, "onStart")
        super.onStart()
    }

    override fun onResume() {
        touchable = true
        LogUtil.e(javaClass.simpleName, "onResume")
        super.onResume()
        doOnResume()
        loadJudgment()
    }

    override fun onPause() {
        touchable = false
        LogUtil.e(javaClass.simpleName, "onPause")
        super.onPause()
    }

    override fun onStop() {
        LogUtil.e(javaClass.simpleName, "onStop")
        super.onStop()
    }

    override fun onDestroyView() {
        LogUtil.e(javaClass.simpleName, "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        LogUtil.e(javaClass.simpleName, "onDestroy")
        super.onDestroy()
    }

    override fun onDetach() {
        alive = false
        doOnDestroy()
        LogUtil.e(javaClass.simpleName, "onDetach")
        super.onDetach()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        LogUtil.e(javaClass.simpleName, "onSaveInstanceState     " + outState)
        super.onSaveInstanceState(outState)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        LogUtil.e(javaClass.simpleName, "onHiddenChanged     " + hidden)
        super.onHiddenChanged(hidden)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        LogUtil.e(javaClass.simpleName, "onConfigurationChanged     " + newConfig)
        super.onConfigurationChanged(newConfig)
    }

    private fun loadJudgment() {
        if (touchable && showing && !isLoaded) {
            isLoaded = true
            onLazyLoad()
        }
    }

    protected fun onLazyLoad() {
        //do nothing
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        showing = isVisibleToUser
        LogUtil.e(javaClass.simpleName, "setUserVisibleHint")
        super.setUserVisibleHint(isVisibleToUser)
        loadJudgment()
    }

    fun postOnResume(doResume: () -> Unit) {
        if(!onResumeList.contains(doResume)){
            onResumeList.add(doResume)
        }
    }

    private fun doOnResume() {
        val iterator = onResumeList.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            next.invoke()
            iterator.remove()
        }
    }

    fun toast(content: String, duration: Int = Toast.LENGTH_SHORT) {
        mToast?.cancel()
        mToast = Toast.makeText(context, content, duration)
        mToast?.show()
    }

    fun getBaseActivity(): BaseActivity {
        return activity as BaseActivity
    }

    private fun doOnDestroy() {
        val iterator = onResumeList.iterator()
        while (iterator.hasNext()) {
            val next = iterator.next()
            next.invoke()
            iterator.remove()
        }
    }
}
