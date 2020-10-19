package com.ooftf.master.sign.ui.register

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.arch.frame.mvvm.activity.BaseMvvmActivity
import com.ooftf.master.sign.databinding.ActivityRegisterBinding
import com.ooftf.service.constant.RouterPath

import kotlinx.android.synthetic.main.activity_register.*

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@Route(path = RouterPath.SIGN_ACTIVITY_REGISTER, extras = 1)
class RegisterActivity : BaseMvvmActivity<ActivityRegisterBinding, RegisterViewModel>() {

}