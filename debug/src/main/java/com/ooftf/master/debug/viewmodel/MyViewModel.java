package com.ooftf.master.debug.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ooftf.master.debug.bean.User;

public class MyViewModel extends ViewModel {
    private MutableLiveData<User> scope;

    public LiveData<User> getScope() {
        if (scope == null) {
            scope = new MutableLiveData();
        }
        loadScope();
        return scope;
    }

    public void loadScope() {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                scope.setValue(new User("lihang", 18));
            }
        }, 2000);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                User value = scope.getValue();
                if (value != null) {
                    value.setName("ligouhai");
                }
                scope.setValue(value);
            }
        }, 5000);

    }
}
