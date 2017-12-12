package tf.oof.com.service.engine

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
 * @param tagId   fragment的tag;建议以按钮的id作为tagId
 */
class FragmentSwitchManager(
        private var manager: FragmentManager,
        private var containerViewId: Int,
        vararg tagId: Int,
        private val onDoSwitch: ((Int, Fragment) -> Unit)?,
        private val createFragment: (Int) -> Fragment) {
    private var tags: IntArray = tagId

    fun switchFragment(tagId: Int) {
        val fragmentTransaction = manager.beginTransaction()
        hideAll(fragmentTransaction)
        var fragment: Fragment = findFragment(fragmentTransaction, tagId)
        onDoSwitch?.invoke(tagId, fragment)
        fragmentTransaction.show(fragment).commitAllowingStateLoss()
    }

    private fun hideAll(fragmentTransaction: FragmentTransaction) {
        tags.map { manager.findFragmentByTag(it.toString()) }
                .filter { it != null }
                .forEach { fragmentTransaction.hide(it) }
    }

    private fun findFragment(fragmentTransaction: FragmentTransaction, tagId: Int): Fragment {
        var fragment: Fragment? = manager.findFragmentByTag(tagId.toString())
        if (null == fragment) {
            fragment = createFragment(tagId)
            fragmentTransaction.add(containerViewId, fragment, tagId.toString())
        }
        return fragment
    }
}
