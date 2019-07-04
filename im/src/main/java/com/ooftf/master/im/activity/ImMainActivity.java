package com.ooftf.master.im.activity;

import android.os.Bundle;
import android.util.SparseArray;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ooftf.bottombar.java.FragmentSwitchManager;
import com.ooftf.master.im.R;
import com.ooftf.master.im.R2;
import com.ooftf.master.im.other.ImManager;
import com.ooftf.service.base.BaseActivity;
import com.ooftf.service.constant.RouterPath;

import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author 99474
 */
@Route(path = RouterPath.IM_ACTIVITY_MAIN)
public class ImMainActivity extends BaseActivity {
    static{
        ImManager.init();
    }
    public static final String TAG_CONVERSATION = "conversation";
    public static final String TAG_CONTACT = "contact";
    @BindView(R2.id.container)
    FrameLayout container;
    @BindView(R2.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    FragmentSwitchManager<String> fsm;
    SparseArray<String> kv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im_main);
        ButterKnife.bind(this);
        initFSM();
        initBottomBar();
    }

    private void initFSM() {
        kv = new SparseArray<>();
        Set<String> tags = new HashSet<>();
        kv.put(0, TAG_CONVERSATION);
        kv.put(1, TAG_CONTACT);
        for (int i = 0; i < kv.size(); i++) {
            tags.add(kv.get(i));
        }
        fsm = new FragmentSwitchManager<String>(getSupportFragmentManager(), R.id.container, tags, tag -> {
            if (TAG_CONVERSATION.equals(tag)) {
                return (Fragment) ARouter.getInstance().build("/im/fragment/conversation").navigation();
            } else {
                return (Fragment) ARouter.getInstance().build("/im/fragment/contact").navigation();
            }

        });
    }

    private void initBottomBar() {
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_conversation, "消息"))
                .addItem(new BottomNavigationItem(R.drawable.ic_contact, "通讯录"))
                .setTabSelectedListener(new BottomNavigationBar.SimpleOnTabSelectedListener() {
                    @Override
                    public void onTabSelected(int position) {
                        fsm.switchFragment(kv.get(position));
                    }
                })
                .setFirstSelectedPosition(0)
                .initialise();
        fsm.switchFragment(kv.get(0));
    }


    @Override
    public boolean isDarkFont() {
        return true;
    }

    @Override
    public boolean isImmersionEnable() {
        return true;
    }

}
