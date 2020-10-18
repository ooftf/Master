package com.ooftf.master.sign.ui.sign

import android.os.Bundle
import android.view.MenuItem
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.arch.frame.mvvm.activity.BaseMvvmActivity
import com.ooftf.master.sign.databinding.ActivitySignInBinding
import com.ooftf.service.constant.RouterPath
import com.ooftf.service.engine.router.service.IMultiSignService
import kotlinx.android.synthetic.main.activity_sign_in.*

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@Route(path = RouterPath.SIGN_ACTIVITY_SIGN_IN, extras = 1)
class SignInActivity : BaseMvvmActivity<ActivitySignInBinding, SignViewModel>(){

    @JvmField
    @Autowired
    var multiAccountService: IMultiSignService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        initToolbar()
    }
    private fun initToolbar() {
        toolbar.menu.add("Google").setOnMenuItemClickListener { item: MenuItem? -> false }.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)
        toolbar.menu.add("Mob").setOnMenuItemClickListener { item: MenuItem? -> false }.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER)
    }
}