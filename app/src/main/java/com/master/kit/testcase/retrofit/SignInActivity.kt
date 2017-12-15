package com.master.kit.testcase.retrofit
import android.os.Bundle
import android.widget.LinearLayout
import com.dks.master.masterretrofit.BaseBean
import com.master.kit.R
import com.master.kit.testcase.retrofit.ServiceHolder.service
import com.nineoldandroids.animation.ValueAnimator
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_sign_in.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import tf.oof.com.service.base.BaseSlidingActivity

class SignInActivity : BaseSlidingActivity() {
    var height = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        KeyboardVisibilityEvent.setEventListener(this) { isOpen ->
            if(height == 0){
                height  = imageView.height
            }
            if (isOpen) {
                var animator = ValueAnimator.ofInt(0,-height)
                animator.addUpdateListener {animation->
                    var lp = imageView.layoutParams as LinearLayout.LayoutParams
                    lp.topMargin = animation.animatedValue as Int
                    imageView.requestLayout()
                }
                animator.setDuration(360)
                animator.start()
            } else {
                var animator = ValueAnimator.ofInt(-height,0)
                animator.addUpdateListener {animation->
                    var lp = imageView.layoutParams as LinearLayout.LayoutParams
                    lp.topMargin = animation.animatedValue as Int
                    imageView.requestLayout()
                }
                animator.setDuration(360)
                animator.start()
            }
        }
        sign_in.setOnClickListener {
            signInRequest()
        }
    }


    private fun signInRequest() {
        if(picCaptcha.uuid == null){
            toast("获取验证码失败")
            return
        }
        service
                .signIn(name.text.toString(), PWD.text.toString(), pin.text.toString(), picCaptcha.uuid!!)
                .bindToLifecycle(window.decorView)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(SignInObserver(EResponseDialog(this)))
    }


    inner class SignInObserver(viewResponse: IEResponse<BaseBean>) : EControlViewObserver<BaseBean>(viewResponse) {
        override fun onResponseSuccess(bean: BaseBean) {
            super.onResponseSuccess(bean)
            toast("登录成功")
        }
    }

}
