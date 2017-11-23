package com.master.kit.testcase.banner

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import butterknife.ButterKnife
import com.master.kit.R
import com.master.kit.activity.GuideActivity
import com.master.kit.engine.imageloader.ImageLoaderFactory
import com.master.kit.testcase.retrofit.EControlViewObserver
import com.master.kit.testcase.retrofit.IEResponse
import com.master.kit.testcase.retrofit.ServiceHolder
import com.youth.banner.loader.ImageLoaderInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_banner.*
import tf.oof.com.service.base.BaseSlidingActivity


class BannerActivity : BaseSlidingActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)
        setupBanner()
        requestBanner()
        responseLayout.setOnRetryListener { requestBanner() }
        next.setOnClickListener {
            startActivity(GuideActivity::class.java)
        }
    }
    private fun setupBanner() {
        banner.setImageLoader(object :ImageLoaderInterface<ImageView>{
            override fun createImageView(context: Context?): ImageView {
                return ImageView(context);
            }

            override fun displayImage(context: Context, path: Any, imageView: ImageView) {
               val bean = path as BannerBean.BodyEntity.PicListEntity
                ImageLoaderFactory.create().display(context, bean.picUrl, imageView)
            }

        })
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun requestBanner() {
        ServiceHolder
                .service
                .getBanner("1","2")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(HomeObserver(responseLayout,this))
    }
    class HomeObserver(responseView: IEResponse<BannerBean>, target: BannerActivity) : EControlViewObserver<BannerBean, BannerActivity>(responseView, target) {
        override fun onResponseSuccess(bean: BannerBean) {
            super.onResponseSuccess(bean)
            val list = bean.body.picList.map { it.picUrl }
            getTarget()?.banner?.let {
                it.setImages(bean.body.picList)
                it.start()
            }
                    //{ url, view -> ImageLoaderFactory.create().display(getTarget(), url, view) }
        }
    }
}
