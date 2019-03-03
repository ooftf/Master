package com.ooftf.master.other.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ooftf.master.other.R;
import com.ooftf.master.other.R2;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;
import com.ooftf.service.net.etd.bean.BaseBean;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/3/3 0003
 */
@Route(path = RouterPath.OTHER_ACTIVITY_REFLECT_PERFORMANCE_TEST)
public class ReflectPerformanceTestActivity extends BaseActivity {
    @BindView(R2.id.edit)
    EditText editText;
    @BindView(R2.id.reflect_button)
    Button reflectButton;
    @BindView(R2.id.new_button)
    Button newButton;
    @BindView(R2.id.reflect_text)
    Button reflectText;
    @BindView(R2.id.new_text)
    TextView newText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect_performance_test);
        ButterKnife.bind(this);
        reflectButton.setOnClickListener(v -> {
            long start = System.currentTimeMillis();
            for (int i = 0; i < getTimers(); i++) {
                try {
                    Constructor<BaseBean> constructor = BaseBean.class.getConstructor();
                    BaseBean baseBean = constructor.newInstance();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            reflectText.setText(String.valueOf(System.currentTimeMillis() - start));
        });
        newButton.setOnClickListener(v -> {
            long start = System.currentTimeMillis();
            for (int i = 0; i < getTimers(); i++) {
                BaseBean baseBean = new BaseBean();
            }
            newText.setText(String.valueOf(System.currentTimeMillis() - start));
        });
    }

    int getTimers() {
        return Integer.valueOf(editText.getText().toString());
    }
}
