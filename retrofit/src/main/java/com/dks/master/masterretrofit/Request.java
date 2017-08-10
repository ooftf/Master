package com.dks.master.masterretrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by master on 2016/5/23.
 */
public abstract class Request<E extends BaseBean> {
    private ServiceApi api;

    protected Request(){
        api = createApi();
    }

    private ServiceApi createApi() {
        return ServiceApiCreater.getServiceApiInstance();
    }

    Call<E> call;
    public void send(){
        call = newCall(api);
        onPreRequest();
        call.enqueue(new Callback<E>() {
            @Override
            public void onResponse(Call<E> call, Response<E> response) {
                E e = response.body();
                Request.this.onResponse(e);
            }
            @Override
            public void onFailure(Call<E> call, Throwable t) {
                Request.this.onFailure();
            }
        });
    }

    protected void onResponse(E e){
        if(e.isSuccess()){
            onResponseSuccess(e);
        }else{
            Request.this.onResponseFailure(e);

        }
    }
    /**
     * 错误信息的时候调用
     * @param e
     */
    protected abstract void onResponseFailureMessage(E e);

    /**
     * 异地登录的时候调用
     * @param e
     */
    protected abstract void onResponseFailureOtherSignIn(E e);

    /**
     * 会话失效的时候调用
     * @param e
     */
    protected abstract void onResponseFailureSessionExpired(E e);

    /**
     * 返回失败信息的时候调用
     * @param e
     */
    protected  void onResponseFailure(E e){
        if("".equals(e.getCode())){
            onResponseFailureSessionExpired(e);
        }else if("".equals(e.getCode())){
            onResponseFailureOtherSignIn(e);
        }else{
            onResponseFailureMessage(e);
        }
    }

    /**
     * 返回成功信息的时候调用
     * @param e
     */
    protected abstract void onResponseSuccess(E e);

    /**
     * 网络出错的时候调用
     */
    protected abstract void onFailure();

    protected abstract Call<E> newCall(ServiceApi api);

    /**
     * 发起网络请求之前调用
     */
    protected abstract void onPreRequest();
    
}
