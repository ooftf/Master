package com.ooftf.widget.modules.banner

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.ooftf.master.session.f.common.IImageLoader
import com.youth.banner.adapter.BannerAdapter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.scopes.ActivityScoped

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2021/8/27
 */
class TheBannerAdapter @AssistedInject constructor (@Assisted datas: MutableList<String>?, var imageLoader: IImageLoader) : BannerAdapter<String, RecyclerView.ViewHolder>(datas) {
    @AssistedFactory
    interface TheBannerAdapterFactory{
        fun create(data: MutableList<String>?):TheBannerAdapter
    }

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val imageView = ImageView(parent.context)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.scaleType  = ImageView.ScaleType.CENTER_CROP
        return object : RecyclerView.ViewHolder(imageView) {}
    }

    override fun onBindView(holder: RecyclerView.ViewHolder, data: String, position: Int, size: Int) {
        imageLoader?.display(data, holder.itemView as? ImageView)
    }

}