package com.master.kit.net.etd

import android.annotation.TargetApi
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.util.AttributeSet
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RelativeLayout
import com.master.kit.R
import com.master.kit.bean.PicCaptchaBean
import com.master.kit.net.ServiceHolder
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import tf.oof.com.service.base.BaseActivity

/**
 * Created by master on 2017/10/20 0020.
 */
class PicCaptchaLayout : RelativeLayout, ResponseView<PicCaptchaBean> {
    override fun onRequest(d: Disposable) {
        pic.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
    }
    override fun onComplete() {

    }

    override fun onAttachedToWindow() {
        Log.e("onAttachedToWindow", "onAttachedToWindow")
        super.onAttachedToWindow()
        picCaptchaRequest()
    }

    override fun onFinishInflate() {
        Log.e("onFinishInflate", "onFinishInflate")
        super.onFinishInflate()
    }

    init {
        post {
            Log.e("post", "post")
        }
    }

    private val dialog: ResponseDialog by lazy {
        ResponseDialog(activity)
    }

    private fun picCaptchaRequest() {
        ServiceHolder.service
                .picCaptcha()
                .bindToLifecycle(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(PicCaptchaObserver(this))
    }

    override fun onError(t:Throwable) {
        Log.e("onError", "onError")
        pic.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
        pic.setImageResource(R.drawable.vector_net_error)
    }

    override fun onResponse(bean:PicCaptchaBean) {
        pic.visibility = View.VISIBLE
        progressBar.visibility = View.INVISIBLE
    }

    override fun onResponseSuccess(bean: PicCaptchaBean) {
        bean.body?.let {
            val bytes = Base64.decode(
                    it.indentify, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            pic.setImageBitmap(bitmap)
        }
    }

    override fun onResponseFailOffSiteLogin(bean: PicCaptchaBean) {
        pic.setImageResource(R.drawable.vector_net_error)
        dialog.onResponseFailOffSiteLogin(bean)
    }

    override fun onResponseFailSessionOverdue(bean: PicCaptchaBean) {
        pic.setImageResource(R.drawable.vector_net_error)
        dialog.onResponseFailSessionOverdue(bean)
    }

    override fun onResponseFailMessage(bean: PicCaptchaBean) {
        Log.e("onResponseFailMessage", "onResponseFailMessage")
        pic.setImageResource(R.drawable.vector_net_error)
        //dialog.onResponseFailMessage(bean)
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    private var pic: ImageView
    private var progressBar: ProgressBar
    private var activity: BaseActivity

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_pic_captcha, this)
        pic = findViewById(R.id.imageView)
        progressBar = findViewById(R.id.progressBar)
        activity = context as BaseActivity
        pic.setOnClickListener { picCaptchaRequest() }
    }
    var uuid :String? = null
   inner class PicCaptchaObserver(viewResponse: ResponseView<PicCaptchaBean>) : PresenterObserver<PicCaptchaBean>(viewResponse){
        override fun onResponseSuccess(bean: PicCaptchaBean) {
            super.onResponseSuccess(bean)
            uuid = bean.body?.uuid
        }
    }
}