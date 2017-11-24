package com.master.kit.testcase.retrofit


import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.util.Log
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.dks.master.masterretrofit.BaseBean
import com.master.kit.R
import com.master.kit.testcase.retrofit.ServiceHolder.service
import com.nineoldandroids.animation.ObjectAnimator
import com.nineoldandroids.animation.ValueAnimator
import com.orhanobut.logger.Logger
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
        service
                .signIn(name.text.toString(), PWD.text.toString(), "sssssssssssss", "ssssssssssssss")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(SignInObserver(EResponseDialog(this), this))
    }


    class SignInObserver(viewResponse: IEResponse<BaseBean>, target: SignInActivity) : EControlViewObserver<BaseBean, SignInActivity>(viewResponse, target) {
        override fun onResponseSuccess(bean: BaseBean) {
            super.onResponseSuccess(bean)
            getTarget()?.toast("登录成功")
        }
    }

}
