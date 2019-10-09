package com.ooftf.service.structure.mvvm.ui;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.alibaba.android.arouter.facade.Postcard;
import com.ooftf.master.statelayout.IStateLayout;
import com.ooftf.service.utils.ThreadUtil;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/6/13 0013
 */
public class BaseLiveData {
    /**
     * //finish
     */
    public LostMutableLiveData<Integer> finishLiveData = new LostMutableLiveData<>();
    /**
     * //finish
     */
    public LostMutableLiveData<FinishData> finishWithData = new LostMutableLiveData<>();
    /**
     * start
     */
    public LostMutableLiveData<Postcard> startActivityLiveData = new LostMutableLiveData<>();


    /**
     * errorMessage
     */
    public LostMutableLiveData<String> messageLiveData = new LostMutableLiveData<>();


    /**
     * showLoading
     */
    public MutableLiveData<Integer> showLoading = new MutableLiveData<>();


    public MutableLiveData<Integer> smartRefresh = new MutableLiveData<>();
    public MutableLiveData<Integer> smartLoadMore = new MutableLiveData<>();


    public MutableLiveData<Integer> stateLayout = new MutableLiveData<>();

    /**
     * finish
     */
    public void finish() {
        finishLiveData.setValue(Activity.RESULT_CANCELED);
    }




    /**
     * finish
     */
    public void finish(int result) {
        finishLiveData.setValue(result);
    }

    /**
     * finish
     */
    public void finish(FinishData result) {
        finishWithData.setValue(result);
    }

    public void startActivity(Postcard postcart) {
        startActivityLiveData.postValue(postcart);
    }

    /**
     * showDialog
     */
    public void showDialog() {
        ThreadUtil.runOnUiThread(() -> {
            if (showLoading.getValue() == null) {
                showLoading.setValue(1);
            } else {
                showLoading.setValue(showLoading.getValue() + 1);
            }
        });
    }

    /**
     * dismissDialog
     */
    public void dismissDialog() {
        ThreadUtil.runOnUiThread(() -> {
            if (showLoading.getValue() == null) {
                showLoading.setValue(0);
            } else {
                showLoading.setValue(showLoading.getValue() - 1);
            }
        });
    }

    public void showMessage(String message) {
        this.messageLiveData.postValue(message);
    }


    public void startRefresh() {
        ThreadUtil.runOnUiThread(() -> {
            if (smartRefresh.getValue() == null) {
                smartRefresh.setValue(1);
            } else {
                smartRefresh.setValue(smartRefresh.getValue() + 1);
            }
        });
    }

    public void finishRefresh() {
        ThreadUtil.runOnUiThread(() -> {
            if (smartRefresh.getValue() == null) {
                smartRefresh.setValue(0);
            } else {
                smartRefresh.setValue(smartRefresh.getValue() - 1);
            }
        });
    }

    public void finishLoadMore() {
        smartLoadMore.postValue(1);
    }

    public void finishLoadMoreWithNoMoreData() {
        smartLoadMore.postValue(2);
    }


    public void switchToEmpty() {
        ThreadUtil.runOnUiThread(() -> {
            stateLayout.setValue(IStateLayout.STATE_EMPTY);
        });
    }

    public void switchToLoading() {
        ThreadUtil.runOnUiThread(() -> {
            stateLayout.setValue(IStateLayout.STATE_LOAD);
        });
    }

    public void switchToError() {
        ThreadUtil.runOnUiThread(() -> {
            stateLayout.setValue(IStateLayout.STATE_ERROR);
        });
    }

    public void switchToSuccess() {
        ThreadUtil.runOnUiThread(() -> {
            stateLayout.setValue(IStateLayout.STATE_SUCCESS);
        });
    }

  /*  public void observeMessage(MutableLiveData<String> liveData) {
        messageLiveData.observeForever(new Observer<String>() {
            @Override
            public void onChanged(String s) {
                liveData.setValue(s);
            }
        });
    }*/

    public BaseLiveDataObserve attach(LifecycleOwner owner, Activity activity) {
        return new BaseLiveDataObserve(this, owner, activity);
    }
    public BaseLiveDataObserve attach( AppCompatActivity activity) {
        return new BaseLiveDataObserve(this, activity, activity);
    }
    public BaseLiveDataObserve attach(Fragment fragment) {
        return new BaseLiveDataObserve(this, fragment, fragment.getActivity());
    }
}
