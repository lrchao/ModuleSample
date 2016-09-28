package com.lrchao.modulesample.mvp.homepage;

import com.jia.jiacore.mvp.IBasePresenter;
import com.jia.jiacore.service.ApiService;
import com.jia.jiacore.util.LogUtils;
import com.lrchao.modulesample.ModuleApplication;
import com.lrchao.modulesample.network.HomepageRequest;

/**
 * Description: TODO
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/28 下午4:11
 */

public class HomepagePresenter extends IBasePresenter<HomepageContract.View>
        implements HomepageContract.Presenter {

    @Override
    public void loadPageData() {
        LogUtils.e("loadPageData()");
        ModuleApplication.getApplication().startService(ApiService.createIntent(new HomepageRequest()));

        getMvpView().showPageData();
    }
}
