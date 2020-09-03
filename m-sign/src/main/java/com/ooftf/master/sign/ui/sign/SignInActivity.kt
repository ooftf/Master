package com.ooftf.master.sign.ui.sign

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.master.sign.R
import com.ooftf.master.sign.dagger.component.DaggerActivityComponent
import com.ooftf.master.sign.dagger.module.ActivityModule
import com.ooftf.arch.frame.mvvm.activity.BaseActivity
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.engine.router.assist.SignChannelInfo
import com.ooftf.service.engine.router.service.IMultiSignService
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_sign_in.*
import javax.inject.Inject

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@Route(path = RouterPath.SIGN_ACTIVITY_SIGN_IN, extras = 1)
class SignInActivity : BaseActivity(), SignInContract.IView {


    @JvmField
    @Inject
    var presenter: SignInContract.IPresenter? = null

    @JvmField
    @Autowired
    var multiAccountService: IMultiSignService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        ARouter.getInstance().inject(this)
        DaggerActivityComponent
                .builder()
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)
        initToolbar()
        signIn!!.setOnClickListener { v: View? -> presenter!!.signIn() }
        register!!.setOnClickListener { v: View? -> ARouter.getInstance().build(RouterPath.SIGN_ACTIVITY_REGISTER).navigation() }
        initSpinner()
        presenter!!.onAttach(this)
    }

    @SuppressLint("CheckResult")
    private fun initSpinner() {
        Observable
                .fromIterable(multiAccountService!!.allChannel)
                .map { accountInfo: SignChannelInfo -> accountInfo.name }
                .toList()
                .subscribe { strings: List<String> -> spinner!!.adapter = ArrayAdapter<String>(this@SignInActivity, R.layout.item_spinner_text, strings) }
        val allChannel = multiAccountService!!.allChannel
        for (i in allChannel.indices) {
            if (allChannel[i].id == multiAccountService!!.currentChannelId) {
                spinner!!.setSelection(i)
                break
            }
        }
    }

    override fun onDestroy() {
        presenter!!.onDetach()
        super.onDestroy()
    }

    private fun initToolbar() {
        toolbar!!.menu.add("Google").setOnMenuItemClickListener { item: MenuItem? -> false }.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)
        toolbar!!.menu.add("Mob").setOnMenuItemClickListener { item: MenuItem? -> false }.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)
    }

    override fun getUsername(): String {
        return account.text.toString()
    }

    override fun getPassword(): String {
        return passwordView.text.toString()
    }

    override fun getChannelId(): String {
        return multiAccountService!!.allChannel[spinner!!.selectedItemPosition].id
    }

    override fun getSinInLoadingButton(): Button {
        return signIn
    }

    override fun nextActivity() {
        finishSuccess()
    }
}