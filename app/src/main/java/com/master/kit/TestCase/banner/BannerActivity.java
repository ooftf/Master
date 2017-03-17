package com.master.kit.testcase.banner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.master.kit.R;
import com.master.kit.testcase.retrofit.ApiService;
import com.master.kit.wapper.image_loader.ImageLoaderFactory;
import com.ooftf.banner.Banner;
import com.ooftf.banner.BannerPagerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BannerActivity extends AppCompatActivity {
    private static final String TGA =  Thread.currentThread().getStackTrace()[1].getClassName();
    @BindView(R.id.banner)
    Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        ButterKnife.bind(this);
        initBanner();
    }

    private void initBanner() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Log.e("request",chain.request()+"");
                Log.e("connection",chain.connection()+"");
                Log.e("toString",chain.toString()+"");
                okhttp3.Response response = chain.proceed(chain.request());
                Log.e("body",response.body().string()+"");
                Log.e("message",response.message()+"");
                return response;
            }
        }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl("https://api.etongdai.com/")
                .client(okHttpClient)
                .build();
        ApiService api = retrofit.create(ApiService.class);
        Call<BannerBean> call = api.getBanner("1", "2");
        call.enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                BannerBean result = response.body();
                List<BannerBean.BodyEntity.PicListEntity> picList = result.getBody().getPicList();
                List<String> list = new ArrayList<>();
                for(BannerBean.BodyEntity.PicListEntity e:picList){
                    list.add(e.getPicUrl());
                }
                banner.setUris(list, new BannerPagerAdapter.DisplayImageCallback() {
                    @Override
                    public void displayImage(String url, ImageView view) {
                        ImageLoaderFactory.create().displayImage(BannerActivity.this,url,view);
                    }
                });

            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {
                log(t.getMessage());
            }
        });
    }
    private void log(String result) {
        if (result == null)
            return;
        int step = 3000;
        for (int i = 0; i < result.length(); i = i + step) {
            if (i + step >= result.length()) {
                Log.e(TGA,"请求服务器返回数据 " + i / step + " : "
                        + result.substring(i, result.length()));
            } else {
                Log.e(TGA,"请求服务器返回数据 " + i / step + ": "
                        + result.substring(i, i + step));
            }

        }

    }
}
