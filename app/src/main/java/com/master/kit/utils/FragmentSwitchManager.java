package com.master.kit.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import static android.R.attr.id;


/**
 * Created by master on 2017/9/27 0027.
 */

public class FragmentSwitchManager {
    FragmentManager manager;
    int[] tags;
    int containerViewId;
    FragmentSwitchListener listener;
    /**
     *
     * @param manager
     * @param containerViewId
     * @param tagId   fragment的tag;建议以按钮的id作为tagId
     */
    public FragmentSwitchManager(FragmentSwitchListener listener, FragmentManager manager, int containerViewId, int... tagId) {
        this.manager = manager;
        tags = tagId;
        this.containerViewId = containerViewId;
        this.listener = listener;
    }

    public void switchFragment(int tagId) {
        String tag = String.valueOf(tagId);
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        hideAll(fragmentTransaction);
        Fragment fragment = manager.findFragmentByTag(tag);
        if (null == fragment) {
            fragment = listener.createFragment(tagId);
            fragmentTransaction.add(containerViewId, fragment, tag);
        }
        listener.onSwitchFragment(tagId,fragment);
        fragmentTransaction.show(fragment).commitAllowingStateLoss();
    }

    private void hideAll(FragmentTransaction fragmentTransaction) {
        for (int i = 0; i < tags.length; i++) {
            Fragment fragment;
            fragment = manager.findFragmentByTag(String.valueOf(tags[i]));
            if (fragment != null) {
                fragmentTransaction.hide(fragment);
            }
        }
    }
    public interface FragmentSwitchListener {
        Fragment createFragment(int id);
        void onSwitchFragment(int id,Fragment fragment);
    }
}
