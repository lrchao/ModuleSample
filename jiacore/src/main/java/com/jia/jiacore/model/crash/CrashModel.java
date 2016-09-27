package com.jia.jiacore.model.crash;

/**
 * Description: 崩溃对象
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/26 下午6:04
 */

public class CrashModel {

    /**
     * 崩溃日志
     */
    private String mCrashLog;

    /**
     * 崩溃线程
     */
    private String mThreadDetails;

    /**
     * 发送的时间
     */
    private long mTime;

    /**
     * 页面名称
     */
    private String mPageName;

    public void setPageName(String pageName) {
        mPageName = pageName;
    }

    public void setTime(long time) {
        mTime = time;
    }


    public void setCrashLog(String crashLog) {
        mCrashLog = crashLog;
    }

    public void setThreadDetails(String threadDetails) {
        mThreadDetails = threadDetails;
    }

    @Override
    public String toString() {
        return "CrashModel{" +
                ", mCrashLog='" + mCrashLog + '\'' +
                ", mThreadDetails='" + mThreadDetails + '\'' +
                ", mTime=" + mTime +
                '}';
    }
}
