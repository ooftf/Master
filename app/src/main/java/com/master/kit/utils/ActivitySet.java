package com.master.kit.utils;

import android.app.Activity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by master on 2016/3/3.
 */
public class ActivitySet<T extends Activity> extends HashSet<T> {
    private ActivitySet() {

    }

    public static ActivitySet<Activity> getInstance() {
        if (activities == null) {
            synchronized (ActivitySet.class) {
                if (activities == null)
                    activities = new ActivitySet<Activity>();
            }

        }
        return activities;

    }

    private static ActivitySet<Activity> activities;

    public void finishAll() {
        for (Activity a : this) {
            a.finish();
        }
    }

    public void finishActiviy(Class cla) {
        for (Activity a : this) {
            if (a.getClass().equals(cla)) {
                a.finish();

            }
        }
    }

    public void finishOther(Activity activity) {
        for (Activity a : this) {
            if (a != activity) {
                a.finish();
            }
        }
    }

    public void finishOther(Class... clas) {
        for (Activity a : this) {
            boolean isContains = false;
            for (int i = 0; i < clas.length; i++) {
                if (clas[i] == a.getClass()) {
                    isContains = true;
                    break;
                }
            }
            if (!isContains) a.finish();
        }
    }

    public void finishOther(Set<Class> classes) {
        for (Activity a : this) {
            if (!classes.contains(a.getClass())) {
                a.finish();
            }
        }
    }


}
