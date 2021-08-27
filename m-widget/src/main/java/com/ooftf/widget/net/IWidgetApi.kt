package com.ooftf.widget.net

import com.ooftf.widget.modules.banner.BannerBean
import retrofit2.http.GET

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2021/8/27
 */
interface IWidgetApi {

    @GET("banner/json")
    suspend fun getBanner(): BannerBean

}