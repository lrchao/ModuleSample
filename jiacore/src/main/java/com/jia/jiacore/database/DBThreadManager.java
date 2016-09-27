package com.jia.jiacore.database;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Description: 处理数据库的线程池
 * 采用单线程的线程池处理
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/27 下午2:03
 */

public final class DBThreadManager {

    private ExecutorService mThreadExecutor;

    private static DBThreadManager sInstance;

    private DBThreadManager() {
        mThreadExecutor = Executors.newSingleThreadExecutor();
    }


    public static DBThreadManager getInstance() {
        synchronized (DBThreadManager.class) {
            if (sInstance == null) {
                sInstance = new DBThreadManager();
            }
        }
        return sInstance;
    }

    public void execute(Runnable r) {
        mThreadExecutor.execute(r);
    }
}
