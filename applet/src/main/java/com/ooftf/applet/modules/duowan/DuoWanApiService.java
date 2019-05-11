package com.ooftf.applet.modules.duowan;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface DuoWanApiService {
    @GET
    Observable<MainListResponse> get(@Url String url);
    /*@GET("showAd=1&action=l&newsTag=headlineNews&p=1")
    Observable<String> requestList(@Url String url);*/

}
