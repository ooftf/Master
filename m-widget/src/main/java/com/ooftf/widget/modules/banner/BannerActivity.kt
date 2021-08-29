package com.ooftf.widget.modules.banner

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.ooftf.mapping.lib.coroutines.BaseResponseSorting
import com.ooftf.service.base.BaseSlidingActivity
import com.ooftf.widget.databinding.ActivityBannerBinding
import com.ooftf.widget.net.IWidgetApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@Route(path = "/widget/activity/banner")
@AndroidEntryPoint
class BannerActivity : BaseSlidingActivity() {
    lateinit var binding:ActivityBannerBinding
    @Inject
    lateinit var api:IWidgetApi

    @Inject
    lateinit var factory: TheBannerAdapter.TheBannerAdapterFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBannerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBanner()
        requestBanner()
        binding.next.setOnClickListener {

        }
    }

    lateinit var  bannerAdapter:TheBannerAdapter
    private fun setupBanner() {
        bannerAdapter = factory.create(null)
        binding.banner.setAdapter(bannerAdapter)
        binding.banner.addBannerLifecycleObserver(this)
    }

    private fun requestBanner() {
        lifecycleScope.launch {
            BaseResponseSorting<BannerBean>().run {
                api.getBanner()
            }
        }
    }

}


