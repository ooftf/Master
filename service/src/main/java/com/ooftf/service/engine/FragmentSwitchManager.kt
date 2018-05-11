package com.ooftf.service.engine

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction


/**
 * Created by master on 2017/9/27 0027.
 */
/**
 *
 * @param manager
 * @param containerViewId
 * @param tagId   fragment的tag
 */
class FragmentSwitchManager(
        private var manager: FragmentManager,
        private var containerViewId: Int,
        vararg tagId: String,
        private val onPreSwitch: ((String, Fragment) -> Unit)?,
        private val createFragment: (String) -> Fragment) {
    private var tags: Array<out String> = tagId

    fun switchFragment(tagId: String) {
        val fragmentTransaction = manager.beginTransaction()
        hideOther(fragmentTransaction, tagId)
        var fragment: Fragment = getFragment(fragmentTransaction, tagId)
        onPreSwitch?.invoke(tagId, fragment)
        fragmentTransaction.show(fragment).commitAllowingStateLoss()
    }

    private fun hideOther(fragmentTransaction: FragmentTransaction, tagId: String) {
        tags.filter { !it.equals(tagId) }
                .map { manager.findFragmentByTag(it.toString()) }
                .filter { it != null }
                .forEach { fragmentTransaction.hide(it) }
    }

    private fun getFragment(fragmentTransaction: FragmentTransaction, tagId: String): Fragment {
        var fragment: Fragment? = manager.findFragmentByTag(tagId.toString())
        if (null == fragment) {
            fragment = createFragment(tagId)
            fragmentTransaction.add(containerViewId, fragment, tagId)
        }
        return fragment
    }
}
