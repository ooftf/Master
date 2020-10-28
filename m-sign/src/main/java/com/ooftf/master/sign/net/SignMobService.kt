package com.ooftf.master.sign.net

import com.ooftf.mapping.lib.LiveDataOperator
import com.ooftf.master.session.net.IServiceCreator
import com.ooftf.master.sign.bean.SignInBean
import com.ooftf.master.session.net.MobBaseBean
import com.ooftf.service.utils.extend.navigation
import retrofit2.http.GET
import retrofit2.http.Query

interface SignMobService {
    @GET("user/login")
    fun signIn(@Query("cellphone") username: String, @Query("password") password: String): LiveDataOperator<SignInBean>

    @GET("user/rigister")
    fun register(@Query("username") username: String, @Query("password") password: String): LiveDataOperator<MobBaseBean>

    companion object {
        private val instance = IServiceCreator::class.java.navigation()
                .create(SignMobService::class.java)

        operator fun invoke(): SignMobService {
            return instance
        }
    }
}