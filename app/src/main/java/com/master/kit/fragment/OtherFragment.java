package com.master.kit.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.master.kit.R;
import com.master.kit.adapter.MainRecyclerAdapter;
import tf.oof.com.service.base.BaseActivity;
import tf.oof.com.service.base.BaseFragment;
import com.master.kit.testcase.AlbumActivity;
import com.master.kit.testcase.CameraActivity;
import com.master.kit.testcase.annotation.AnnotationActivity;
import com.master.kit.testcase.softkeyboard.SoftKeyboardActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by master on 2017/9/26 0026.
 */

public class OtherFragment extends BaseHomeFragment {
    public static OtherFragment newInstance() {

        Bundle args = new Bundle();

        OtherFragment fragment = new OtherFragment();
        fragment.setArguments(args);
        return fragment;
    }
    protected void initData() {
        adapter.add(AlbumActivity.class);
        adapter.add(AnnotationActivity.class);
        adapter.add(CameraActivity.class);
        adapter.add(SoftKeyboardActivity.class);
        adapter.notifyDataSetChanged();
    }
}
