package com.jia.jiacore.mvp;

import android.support.annotation.CallSuper;

/**
 * Description: TODO
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/28 上午9:53
 */

public abstract class IBasePresenter<T extends MvpView> implements MvpPresenter<T> {

    /**
     * Mvp的view
     */
    private T mMvpView;

    //=============================================
    // 外部可调用
    //=============================================

    /**
     * 获取MvpView
     */
    public final T getMvpView() {
        return mMvpView;
    }

    //=============================================
    // 内部的
    //=============================================

    /**
     * 添加MvpView
     *
     * @param mvpView MvpView
     */
    @Override
    public final void attachView(T mvpView) {
        mMvpView = mvpView;
    }

    /**
     * 移除MvpView
     */
    @Override
    public final void detachView() {
        mMvpView = null;
    }

    @CallSuper
    @Override
    public void start() {
    }

    @CallSuper
    @Override
    public void end() {
    }
}
