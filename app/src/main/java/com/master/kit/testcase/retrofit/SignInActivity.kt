package com.master.kit.testcase.retrofit

import android.os.Bundle
import android.widget.LinearLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.master.kit.R
import com.nineoldandroids.animation.ValueAnimator
import com.ooftf.hihttp.action.DialogAction
import com.ooftf.service.base.BaseSlidingActivity
import com.ooftf.service.net.ServiceHolder.service
import com.ooftf.service.net.etd.BaseResponse
import com.ooftf.service.net.etd.action.ErrorAction
import com.ooftf.service.net.etd.bean.BaseBean
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_sign_in.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import tf.ooftf.com.service.engine.inputfilter.RegexInputFilter

@Route(path = "/main/signIn")
class SignInActivity : BaseSlidingActivity() {
    var height = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        KeyboardVisibilityEvent.setEventListener(this) { isOpen ->
            if (height == 0) {
                height = imageView.height
            }
            if (isOpen) {
                var animator = ValueAnimator.ofInt(0, -height)
                animator.addUpdateListener { animation ->
                    var lp = imageView.layoutParams as LinearLayout.LayoutParams
                    lp.topMargin = animation.animatedValue as Int
                    imageView.requestLayout()
                }
                animator.duration = 360
                animator.start()
            } else {
                var animator = ValueAnimator.ofInt(-height, 0)
                animator.addUpdateListener { animation ->
                    var lp = imageView.layoutParams as LinearLayout.LayoutParams
                    lp.topMargin = animation.animatedValue as Int
                    imageView.requestLayout()
                }
                animator.duration = 360
                animator.start()
            }
        }
        sign_in.setOnClickListener {
            signInRequest()
        }
        name.filters = arrayOf(RegexInputFilter(RegexInputFilter.REGEX_CN_NUMBER_LETTER))
    }


    private fun signInRequest() {
        if (picCaptcha.uuid == null) {
            toast("获取验证码失败")
            return
        }
        service
                .signIn(name.text.toString(), PWD.text.toString(), pin.text.toString(), picCaptcha.uuid!!)
                .bindToLifecycle(window.decorView)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(DialogAction(this))
                .compose(ErrorAction<BaseBean>(this))
                .subscribe(object : BaseResponse<BaseBean>() {
                    override fun onSuccess(t: BaseBean?) {
                        toast("登录成功")
                    }

                })
    }

}
