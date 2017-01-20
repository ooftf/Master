package com.master.kit.testcase.retrofit;

import okhttp3.ResponseBody;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by master on 2017/1/19.
 */

public interface ApiService {
    @Streaming
    Observable<ResponseBody> downloadFile(@Url String url);
}
