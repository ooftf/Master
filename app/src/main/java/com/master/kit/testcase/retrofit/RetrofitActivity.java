package com.master.kit.testcase.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.master.kit.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        Retrofit retrofit = new Retrofit.Builder().build();
        //TODO 没有构建完成
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<ResponseBody> responseBodyObservable = apiService.downloadFile("");
        responseBodyObservable.subscribe(new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                InputStream inputStream = responseBody.byteStream();
                try {
                    OutputStream outputStream = new FileOutputStream("");
                    for(byte[] bytes = new byte[1024];inputStream.read(bytes)!=-1;){
                        outputStream.write(bytes);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
