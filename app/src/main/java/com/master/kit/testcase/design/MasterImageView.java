package com.master.kit.testcase.design;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.widget.ImageView;

/**
 * Created by master on 2016/6/2.
 */
public class MasterImageView extends CoordinatorLayout.Behavior<ImageView> {
   /* @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
       retur modifyAvatarDependingDependencyState(child, dependency);
    }

    private void modifyAvatarDependingDependencyState(
            ImageView avatar, View dependency) {
        //  avatar.setY(dependency.getY());
        //  avatar.setBlahBlah(dependency.blah / blah);
    }*/
}
