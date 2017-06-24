package com.master.kit.testcase.retrofit;

import com.master.kit.testcase.banner.BannerBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by master on 2017/1/19.
 */

public interface ApiService {
    @Streaming
    Observable<ResponseBody> downloadFile(@Url String url);

    @POST("service/more/index")
    @FormUrlEncoded
    Call<BannerBean> getBanner(@Field("useClientVersion") String userClientVersion, @Field("terminalType") String terminalType);
}
