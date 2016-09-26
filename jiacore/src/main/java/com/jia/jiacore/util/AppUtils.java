package com.jia.jiacore.util;

import android.app.ActivityManager;
import android.content.Context;

import com.jia.jiacore.IBaseApplication;

/**
 * Description: 应用相关的工具类
 * 例如获取包名，应用图标等
 *
 * @author lrc19860926@gmail.com
 * @date 16/3/17 上午10:52
 */
public final class AppUtils {

    private AppUtils() {

    }

    /**
     * 获得当前进程的名字
     *
     * @return 进程号
     */
    public static String getCurProcessName() {

        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) IBaseApplication.getApplication()
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
