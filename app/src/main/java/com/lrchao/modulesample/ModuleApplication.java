package com.lrchao.modulesample;

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
    }

    @Override
    protected boolean isDEBUG() {
        return BuildConfig.DEBUG;
    }
}
