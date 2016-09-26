package com.jia.jiacore.ui.fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jia.jiacore.util.LogUtils;

/**
 * Description: Fragment基类
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/26 下午2:53
 */

public abstract class IBaseFragment extends Fragment {

    /**
     * 对应的Fragment Name
     */
    protected final String mTag = this.getClass().getSimpleName();

    /**
     * FragmentManager
     */
    private FragmentManager mFragmentManager;

    /**
     * Fragment的根view
     */
    protected View mMainView;

    //====================================
    // 子类继承
    //====================================

    /**
     * 扩展的fragment,嵌套fragment使用
     *
     * @param inflater     LayoutInflater
     * @param container    ViewGroup
     * @param attachToRoot boolean
     * @return 实际的view
     */
    protected View getLayoutView(LayoutInflater inflater, ViewGroup container, boolean attachToRoot) {
        return null;
    }

    /**
     * 设置初始化的layout view
     *
     * @return Layout View
     */
    protected abstract int getLayoutViewId();

    /**
     * 初始化Fragment的view的方法
     *
     * @param parentView 根view
     */
    protected abstract void initView(View parentView);


    //====================================
    // 内部方法
    //====================================

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d(mTag, "onCreate()");
        mFragmentManager = getChildFragmentManager();

        // 用于延迟加载
        getActivity().getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                //onDelayLoad();
            }
        });

        // mMainHandler = new MainHandler(new WeakReference<>(this));
    }

    @CallSuper
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mMainView == null) {
            if (getLayoutView(inflater, container, false) != null) {
                mMainView = getLayoutView(inflater, container, false);
            } else {
                mMainView = inflater.inflate(getLayoutViewId(), container, false);
            }
            initView(mMainView);

        } else {
            ViewGroup parent = (ViewGroup) mMainView.getParent();
            if (null != parent) {
                parent.removeView(mMainView);
            }
        }
        return mMainView;
    }

}
