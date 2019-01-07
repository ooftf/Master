package com.ooftf.widget.adapter

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/11/6 0006
 */

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.alibaba.android.arouter.launcher.ARouter
import com.ooftf.service.base.adapter.BaseViewHolder
import com.ooftf.service.bean.ScreenItemBean
import com.ooftf.service.utils.DensityUtil
import com.ooftf.spiale.SpialeLayout
import com.ooftf.widget.R
import com.youth.banner.Banner
import com.youth.banner.loader.ImageLoaderInterface


/**
 * Created by master on 2017/9/25 0025.
 */

open class WidgetAdapter : RecyclerView.Adapter<BaseViewHolder<View>>() {
    var body = ArrayList<ScreenItemBean>()
    var spialeList = ArrayList<String>()
    var bannerList = object : ArrayList<Int>() {
        init {
            add(R.drawable.tiaotiao1)
            add(R.drawable.tiaotiao2)
            add(R.drawable.tiaotiao3)
            add(R.drawable.tiaotiao4)
            add(R.drawable.tiaotiao5)
            add(R.drawable.tiaotiao6)
            add(R.drawable.tiaotiao7)
            add(R.drawable.tiaotiao8)
            add(R.drawable.tiaotiao9)
            add(R.drawable.tiaotiao10)
            add(R.drawable.tiaotiao11)
            add(R.drawable.tiaotiao12)
            add(R.drawable.tiaotiao13)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<View> {
        return when (viewType) {
            BANNER_TYPE -> createBannerHolder(parent)
            SPIALE_TYPE -> createSpialeHolder(parent)
            else -> BodyHolder(R.layout.item_recyclerview_main, parent)
        }
    }

    private fun createBannerHolder(parent: ViewGroup): BaseViewHolder<View> {
        val bannerHolder = BaseViewHolder<Banner>(Banner(parent.context))
        var height = DensityUtil.getScreenWidthPixels(parent.context) * 0.8f//1440f / 1080f
        bannerHolder.itemView.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height.toInt())
        bannerHolder.getItemView().setImageLoader(object : ImageLoaderInterface<ImageView> {
            override fun createImageView(context: Context): ImageView? {
                return null
            }

            override fun displayImage(context: Context, path: Any, imageView: ImageView) {
                imageView.setImageResource(path as Int)
            }

        })
        bannerHolder.getItemView().isAutoPlay(true)
        return bannerHolder as BaseViewHolder<View>
    }

    private fun createSpialeHolder(parent: ViewGroup): SpialeHolder {
        val spialeHolder = SpialeHolder(com.ooftf.widget.R.layout.item_widget_todo, parent)
        spialeHolder.spialeLayout.adapter = WidgetSpialeAdapter()
        return spialeHolder
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> BANNER_TYPE
            1 -> SPIALE_TYPE
            else -> super.getItemViewType(position)
        }
    }

    override fun getItemCount(): Int {
        return 1 + 1 + body.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<View>, position: Int) {
        when (getItemViewType(position)) {
            BANNER_TYPE -> bindBannerHolder(holder as BaseViewHolder<Banner>, position)
            SPIALE_TYPE -> bindSpialeHolder(holder as SpialeHolder, position)
            else -> bindBodyHolder(holder as BodyHolder, position)
        }
    }

    private fun bindBodyHolder(holder: BodyHolder, position: Int) {
        val bean = body[position - 1]
        holder.title.visibility = View.GONE
        holder.title.text = bean.category
        holder.describe.text = bean.describe
        holder.name.text = "${bean.name}(${bean.clz})"
        holder.icon.setImageResource(bean.icon)

        if (bean.isIssue) {
            holder.issue.visibility = View.VISIBLE
        } else {
            holder.issue.visibility = View.INVISIBLE
        }

        holder.itemView.setOnClickListener {
            ARouter.getInstance().build(bean.clz).navigation()
        }
    }

    private fun bindSpialeHolder(holder: SpialeHolder, position: Int) {
        val adapter = holder.spialeLayout.adapter as WidgetSpialeAdapter
        adapter.list = spialeList
        adapter.notifyDataSetChanged()
    }

    private fun bindBannerHolder(holder: BaseViewHolder<Banner>, position: Int) {
        holder.getItemView().setImages(bannerList)
        holder.getItemView().start()

    }


    companion object {
        val SPIALE_TYPE = 1
        val BANNER_TYPE = 2
    }

    class BodyHolder : BaseViewHolder<View> {
        constructor(@LayoutRes layoutId: Int, parent: ViewGroup) : super(layoutId, parent)

        var name: TextView = itemView.findViewById(R.id.name)
        var describe: TextView = itemView.findViewById(R.id.describe)
        var icon: ImageView = itemView.findViewById(R.id.icon)
        var issue: ImageView = itemView.findViewById(R.id.issue)
        var title: TextView = itemView.findViewById(R.id.stickyView)
    }

    class SpialeHolder(@LayoutRes layoutId: Int, parent: ViewGroup) : BaseViewHolder<View>(layoutId, parent) {
        var spialeLayout: SpialeLayout = itemView.findViewById(com.ooftf.widget.R.id.spialeLayout)
    }


}