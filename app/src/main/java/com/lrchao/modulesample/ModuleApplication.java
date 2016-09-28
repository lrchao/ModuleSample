package com.lrchao.modulesample;

import com.jia.jiacore.IBaseApplication;
import com.lrchao.modulesample.constant.Constants;

/**
 * Description: Application
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

    @Override
    protected String getDirNamePlatform() {
        return Constants.DIR_NAME_ZXERP;
    }

    @Override
    protected String getDirNameApp() {
        return Constants.DIR_NAME_USER;
    }
}
