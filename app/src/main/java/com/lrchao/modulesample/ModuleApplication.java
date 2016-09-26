package com.lrchao.modulesample;

import android.util.Log;

import com.jia.jiacore.IBaseApplication;

/**
 * Description: TODO
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/26 上午11:34
 */

public class ModuleApplication extends IBaseApplication {

    @Override
    protected void init() {
        Log.e("bbb", "bbb==" + BuildConfig.DEBUG);
        //setDebug(com.jia.jiacore.BuildConfig);
    }

    @Override
    protected boolean isDEBUG() {
        return BuildConfig.DEBUG;
    }
}
