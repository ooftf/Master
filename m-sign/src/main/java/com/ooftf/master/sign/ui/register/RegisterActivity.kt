package com.ooftf.master.sign.ui.register

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.master.sign.R
import com.ooftf.master.sign.dagger.component.DaggerActivityComponent
import com.ooftf.master.sign.dagger.module.ActivityModule
import com.ooftf.service.base.BaseActivity
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.engine.router.assist.SignChannelInfo
import com.ooftf.service.engine.router.service.IMultiSignService
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_register.*
import javax.inject.Inject

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@Route(path = RouterPath.SIGN_ACTIVITY_REGISTER, extras = 1)
class RegisterActivity : BaseActivity(), RegisterContract.IView {
    @JvmField
    @Autowired
    var multiAccountService: IMultiSignService? = null

    @JvmField
    @Inject
    var presenter: RegisterContract.IPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        ARouter.getInstance().inject(this)
        DaggerActivityComponent.builder().activityModule(ActivityModule(this)).build().inject(this)
        initSpinner()
        presenter!!.onAttach(this)
        register.setOnClickListener { v -> presenter!!.register() }
    }

    @SuppressLint("CheckResult")
    private fun initSpinner() {
        Observable
                .fromIterable(multiAccountService!!.allChannel)
                .map { obj: SignChannelInfo -> obj.name }
                .toList()
                .subscribe { strings: List<String> -> spinner.adapter = ArrayAdapter<String>(this@RegisterActivity, R.layout.item_spinner_text, strings) }
        val allChannel = multiAccountService!!.allChannel
        for (i in allChannel.indices) {
            if (allChannel[i].id == multiAccountService!!.currentChannelId) {
                spinner.setSelection(i)
                break
            }
        }
    }

    override fun getUsername(): String {
        return account.text.toString()
    }

    override fun getPassword(): String {
        return passwordView.text.toString()
    }

    override fun getChannelId(): String {
        return multiAccountService!!.allChannel[spinner.selectedItemPosition].id
    }

    override fun getRegisterLoadingButton(): Button {
        return register
    }

    override fun showSuccessDialog(message: String) {
        showDialogMessage(message, DialogInterface.OnClickListener { _: DialogInterface, _: Int -> finish() })
    }
}