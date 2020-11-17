package com.ooftf.widget.activity

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.service.base.BaseSlidingActivity
import com.ooftf.service.engine.imageloader.IImageLoader
import com.ooftf.service.net.etd.bean.BannerBean
import com.ooftf.widget.R
import com.ooftf.widget.dagger.DaggerBannerComponent
import com.youth.banner.adapter.BannerAdapter
import kotlinx.android.synthetic.main.activity_banner.*
import javax.inject.Inject

@Route(path = "/widget/activity/banner")
class BannerActivity : BaseSlidingActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerBannerComponent.create().inject(this)
        setContentView(R.layout.activity_banner)
        setupBanner()
        requestBanner()
        next.setOnClickListener {

        }
    }

    val bannerAdapter = TheBannerAdapter(null)
    private fun setupBanner() {
        banner.adapter = bannerAdapter
        banner.addBannerLifecycleObserver(this)
    }

    private fun requestBanner() {

    }

}

class TheBannerAdapter(datas: MutableList<BannerBean.BodyEntity.PicListEntity>?) : BannerAdapter<BannerBean.BodyEntity.PicListEntity, RecyclerView.ViewHolder>(datas) {
    @JvmField
    @Autowired
    var imageLoader: IImageLoader?=null

    init {
        ARouter.getInstance().inject(this)
    }

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val imageView = ImageView(parent.context)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.scaleType  = ImageView.ScaleType.CENTER_CROP
        return object : RecyclerView.ViewHolder(imageView) {}
    }

    override fun onBindView(holder: RecyclerView.ViewHolder, data: BannerBean.BodyEntity.PicListEntity, position: Int, size: Int) {
        imageLoader?.display(data.picUrl, holder.itemView as? ImageView)
    }

}
