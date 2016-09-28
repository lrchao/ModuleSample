package com.jia.jiacore.mvp;

/**
 * Description: TODO
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/28 下午4:09
 */

public interface MvpPresenter<V extends MvpView> {

    /**
     * 做一些初始化工作
     */
    void start();

    /**
     * 做一些数据清除工作
     */
    void end();

    /**
     * 关联View
     *
     * @param mvpView MvpView
     */
    void attachView(V mvpView);

    /**
     * 分离View
     */
    void detachView();
}
