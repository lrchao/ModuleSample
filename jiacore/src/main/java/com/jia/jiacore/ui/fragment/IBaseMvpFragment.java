package com.jia.jiacore.ui.fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.jia.jiacore.exception.InitializationNotCompleteException;
import com.jia.jiacore.mvp.IBasePresenter;
import com.jia.jiacore.mvp.MvpView;

/**
 * Description: TODO
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/28 下午4:24
 */

public abstract class IBaseMvpFragment extends IBaseFragment {

    /**
     * Presenter
     */
    protected IBasePresenter mBasePresenter;

    /**
     * 子类实现，负责初始化Presenter
     */
    protected abstract IBasePresenter createPresenter();


    //====================================
    // 外部可调用
    //====================================

    public final IBasePresenter getPresenter() {
        return mBasePresenter;
    }

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPresenter();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBasePresenter != null) {
            mBasePresenter.end();
            mBasePresenter.detachView();
        }
    }

    /**
     * 初始化Presenter
     */
    private void initPresenter() {
        // 设置Presenter
        mBasePresenter = createPresenter();

        if (mBasePresenter != null) {
            if (this instanceof MvpView) {
                mBasePresenter.attachView((MvpView) this);
                mBasePresenter.start();
            } else {
                throw new InitializationNotCompleteException(mTag +
                        " must implements XxxContract.View");
            }
        }
    }
}
