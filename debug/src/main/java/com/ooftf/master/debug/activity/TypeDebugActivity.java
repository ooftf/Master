package com.ooftf.master.debug.activity;

import android.os.Bundle;

import com.ooftf.master.debug.R;
import com.ooftf.service.base.BaseActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author 99474
 * @question getGenericInterfaces的到的是什么
 * ParameterizedType代表的是什么
 */
public class TypeDebugActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_debug);


    }

    public static void main(String[] args) {
        Test test = (Test<String>) () -> {

        };
        Type[] genericInterfaces = test.getClass().getGenericInterfaces();


    }

    interface Test<T> {
        void test();
    }
}
