package com.jia.jiacore;

import android.app.Application;

/**
 * Description: Application的基类
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/26 上午11:14
 */
public abstract class IBaseApplication extends Application {

    private static IBaseApplication sInstance;

    public static IBaseApplication getApplication() {
        return sInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        CoreBuildConfig.DEBUG = isDEBUG();
        init();
    }

    /**
     * 子类初始化
     */
    protected abstract void init();

    protected abstract boolean isDEBUG();
}
