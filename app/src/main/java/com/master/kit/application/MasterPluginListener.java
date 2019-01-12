package com.master.kit.application;

import android.content.Context;

import com.tencent.matrix.plugin.DefaultPluginListener;
import com.tencent.matrix.report.Issue;
import com.tencent.matrix.util.MatrixLog;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/1/3 0003
 */
public class MasterPluginListener extends DefaultPluginListener {
    public static final String TAG = MasterPluginListener.class.getSimpleName();
    public MasterPluginListener(Context context) {
        super(context);
    }
    @Override
    public void onReportIssue(Issue issue) {
        super.onReportIssue(issue);
        MatrixLog.e(TAG, issue.toString());
        //add your code to process data
    }
}
