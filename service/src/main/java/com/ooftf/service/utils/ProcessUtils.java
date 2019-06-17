package com.ooftf.service.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2019/6/17 0017
 */
public class ProcessUtils {
    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }


    public static String getCurrentProcessName() {
        return getProcessName(android.os.Process.myPid());
    }

    public static boolean isMainProcess(Context context) {
        String packageName = context.getPackageName();
        String processName = getCurrentProcessName();
        if (packageName == null) {
            return true;
        }
        if (processName == null) {
            return true;
        }
        return packageName.equals(processName);
    }
}
