package com.jia.jiacore;

import android.app.Application;

import com.jia.jiacore.constant.IBaseConstants;
import com.jia.jiacore.manager.crash.CrashHandler;
import com.jia.jiacore.util.LogUtils;

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
        sInstance = this;

        CoreBuildConfig.DEBUG = isDEBUG();
        IBaseConstants.DIR_NAME_PLATFORM = getDirNamePlatform();
        IBaseConstants.DIR_NAME_APP = getDirNameApp();

        LogUtils.init();
        CrashHandler.getInstance().init();

        init();
    }

    /**
     * 子类初始化
     */
    protected abstract void init();

    /**
     * 是否为Debug模式
     */
    protected abstract boolean isDEBUG();

    /**
     * 博若森 或者 装修平台 等
     */
    protected abstract String getDirNamePlatform();

    /**
     * 具体app的缓存名称
     */
    protected abstract String getDirNameApp();


}
