package com.jia.jiacore.manager.crash;

import android.util.Log;

import com.jia.jiacore.model.crash.CrashModel;
import com.jia.jiacore.util.FileUtils;
import com.jia.jiacore.util.LogUtils;

import java.io.File;

/**
 * Description: 崩溃捕捉
 *
 * @author lrc19860926@gmail.com
 * @date 16/4/11 上午9:54
 */
public final class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler sInstance;

    /**
     * 崩溃对象
     */
    private CrashModel mCrashModel;

    private CrashHandler() {
        mCrashModel = new CrashModel();
    }

    public static CrashHandler getInstance() {
        synchronized (CrashHandler.class) {
            if (sInstance == null) {
                sInstance = new CrashHandler();
            }
        }
        return sInstance;
    }

    /**
     * 初始化
     */
    public void init() {
        if (Thread.getDefaultUncaughtExceptionHandler() != this) {
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        LogUtils.wtf(ex);
        final CrashModel crashModel = createCrashReportModel(thread, ex);
        // 获取缓存的文件路径
        File crashFile = FileUtils.getCrashFile();
        if (crashFile != null) {
            FileUtils.writeDataToSdCard(crashFile.getAbsolutePath(), crashModel.toString());
        }
        endApplication();
    }

    /**
     * 关闭程序
     */
    private void endApplication() {
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }

    /**
     * 生成CrashReportModel
     */
    private CrashModel createCrashReportModel(Thread thread, Throwable ex) {
        mCrashModel.setCrashLog(Log.getStackTraceString(ex));
        mCrashModel.setTime(System.currentTimeMillis());
        mCrashModel.setThreadDetails(collectThreadDetails(thread));
        return mCrashModel;
    }

    /**
     * Convenience method that collects some data identifying a Thread, usually the Thread which
     * crashed and returns a string containing the thread's id, name, priority and group name.
     *
     * @param t the thread
     * @return a string representation of the string including the id, name and priority of the thread.
     */
    public String collectThreadDetails(Thread t) {
        StringBuilder result = new StringBuilder();
        if (t != null) {

            result.append("id=")
                    .append(t.getId())
                    .append("\n");
            result.append("name=")
                    .append(t.getName())
                    .append("\n");
            result.append("priority=")
                    .append(t.getPriority())
                    .append("\n");
            if (t.getThreadGroup() != null) {
                result.append("groupName=")
                        .append(t.getThreadGroup()
                                .getName())
                        .append("\n");
            }
        } else {
            result.append("No broken thread, this might be a silent exception.");
        }
        return result.toString();
    }
}
