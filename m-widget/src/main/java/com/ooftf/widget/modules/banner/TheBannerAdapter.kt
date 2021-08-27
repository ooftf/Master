package com.ooftf.widget.modules.banner

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.master.session.f.common.IImageLoader.IImageLoader
import com.youth.banner.adapter.BannerAdapter

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2021/8/27
 */
class TheBannerAdapter(datas: MutableList<String>?) : BannerAdapter<String, RecyclerView.ViewHolder>(datas) {
    @JvmField
    @Autowired
    var imageLoader: IImageLoader?=null

    init {
        ARouter.getInstance().inject(this)
    }

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val imageView = ImageView(parent.context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.scaleType  = ImageView.ScaleType.CENTER_CROP
        return object : RecyclerView.ViewHolder(imageView) {}
    }

    override fun onBindView(holder: RecyclerView.ViewHolder, data: String, position: Int, size: Int) {
        imageLoader?.display(data, holder.itemView as? ImageView)
    }

}