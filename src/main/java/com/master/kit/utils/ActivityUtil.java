package com.master.kit.utils;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by master on 2016/3/3.
 */
public class ActivityUtil {
    public static ArrayList<Activity> sActivityList;
    static{
        sActivityList = new ArrayList<>();
    }
    public static void sFinishAll(){
        for(Activity a:sActivityList){
            a.finish();
        }
    }
    public static void sFinishActiviy(Class cla){
        for(Activity a:sActivityList){
            if(a.getClass().equals(cla)){
                a.finish();
            }
        }
    }
    public static void sFinishOther(Activity activity){
        for(Activity a:sActivityList){
            if(a!=activity){
                a.finish();
            }
        }
    }

}
