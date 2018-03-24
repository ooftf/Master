package com.ooftf.widget.activity

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.service.base.BaseSlidingActivity
import com.ooftf.service.engine.image_loader.IImageLoader
import com.ooftf.service.net.ServiceHolder
import com.ooftf.service.net.etd.PresenterObserver
import com.ooftf.service.net.etd.ResponseView
import com.ooftf.service.net.etd.bean.BannerBean
import com.ooftf.widget.R
import com.ooftf.widget.dagger.DaggerBannerComponent
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.youth.banner.loader.ImageLoaderInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_banner.*
import javax.inject.Inject

@Route(path = "/widget/banner")
class BannerActivity : BaseSlidingActivity() {

    @Inject lateinit var imageLoader: IImageLoader
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerBannerComponent.create().inject(this)
        setContentView(R.layout.activity_banner)
        setupBanner()
        requestBanner()
        responseLayout.setOnRetryListener { requestBanner() }
        next.setOnClickListener {
            ARouter.getInstance().build("/widget/guide").navigation()
        }
    }

    private fun setupBanner() {
        banner.setImageLoader(object : ImageLoaderInterface<ImageView> {
            override fun createImageView(context: Context): ImageView? {
                return null
            }

            override fun displayImage(context: Context, path: Any, imageView: ImageView) {
                val bean = path as BannerBean.BodyEntity.PicListEntity
                imageLoader.display(context, bean.picUrl, imageView)
            }

        })
    }

    private fun requestBanner() {
        ServiceHolder
                .service
                .getBanner("1", "2")
                .bindToLifecycle(banner)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(HomeObserver(responseLayout))
    }

    inner class HomeObserver(responseView: ResponseView<BannerBean>) : PresenterObserver<BannerBean>(responseView) {
        override fun onResponseSuccess(bean: BannerBean) {
            super.onResponseSuccess(bean)
            banner.setImages(bean.body.picList)
            banner.start()
        }
    }
}
