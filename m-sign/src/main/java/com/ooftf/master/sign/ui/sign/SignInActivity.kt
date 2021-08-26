package com.ooftf.master.sign.ui.sign

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.KeyboardUtils
import com.ooftf.arch.frame.mvvm.activity.BaseMvvmActivity
import com.ooftf.basic.utils.DensityUtil
import com.ooftf.log.JLog
import com.ooftf.master.sign.bean.SignInBean
import com.ooftf.master.sign.databinding.ActivitySignInBinding
import com.ooftf.service.constant.RouterPath
import com.tencent.tauth.Tencent

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/21 0021
 */
@Route(path = RouterPath.SIGN_ACTIVITY_SIGN_IN, extras = 1)
class SignInActivity : BaseMvvmActivity<ActivitySignInBinding, SignViewModel>() {
    private val animator: ValueAnimator by lazy {
        ObjectAnimator.ofFloat(MAX_SIZE, MIN_SIZE).apply {
            addUpdateListener {
                viewModel.marginTop.value = DensityUtil.dip2px(it.animatedValue as Float).toInt()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.baseLiveData.getLiveData(SignInBean::class.java).observe(this) {
            finishSuccess()
        }

        KeyboardUtils.registerSoftInputChangedListener(this){
            val show = it != 0
            viewModel.keyBoardShow.value = show
            if (show) {
                animator.cancel()
                animator.setFloatValues(MAX_SIZE, MIN_SIZE)
                animator.start()
            } else {
                animator.cancel()
                animator.setFloatValues(MIN_SIZE, MAX_SIZE)
                animator.start()
            }
        }
    }

    companion object {
        const val MIN_SIZE = 40f
        const val MAX_SIZE = 84f
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        JLog.e(data?.extras)
        Tencent.onActivityResultData(requestCode,resultCode,data,viewModel.listener)
    }
}