package com.master.kit.testcase.banner;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.master.kit.R;
import com.master.kit.testcase.retrofit.IETongDaiService;
import com.master.kit.engine.imageloader.ImageLoaderFactory;
import com.master.kit.testcase.retrofit.ServiceHolder;
import com.ooftf.banner.Banner;
import com.ooftf.banner.BannerPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tf.oof.com.service.base.BaseSlidingActivity;


public class BannerActivity extends BaseSlidingActivity {
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
        IETongDaiService api = ServiceHolder.INSTANCE.getService();
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
                        ImageLoaderFactory.create().display(BannerActivity.this,url,view);
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

    @Override
    protected void onStart() {
        super.onStart();
        banner.stopCycle();
    }

    @Override
    protected void onStop() {
        super.onStop();
        banner.stopCycle();
    }
}
