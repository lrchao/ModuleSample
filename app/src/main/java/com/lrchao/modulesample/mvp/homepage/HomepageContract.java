package com.lrchao.modulesample.mvp.homepage;

import com.jia.jiacore.mvp.MvpView;

/**
 * Description: TODO
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/28 下午4:12
 */

public class HomepageContract {

    public interface View extends MvpView {

        void showPageData();
    }

    public interface Presenter {

        void loadPageData();
    }
}
