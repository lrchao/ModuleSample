package com.jia.jiacore.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jia.jiacore.ui.dialog.IBaseDialogFragment;
import com.jia.jiacore.util.LogUtils;

import java.lang.ref.WeakReference;

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
     * Handler
     */
    protected MainHandler mMainHandler;
    /**
     * Fragment的根view
     */
    protected View mMainView;
    /**
     * FragmentManager
     */
    private FragmentManager mFragmentManager;

    //====================================
    // 子类调用
    //====================================

    /**
     * Handler的回调方法
     *
     * @param msg Message
     */
    protected final void handleMainMessage(Message msg) {
    }

    /**
     * 关闭Activity
     */
    protected final void finishActivity() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    /**
     * *
     * 调用Activity的setResult()
     *
     * @param intent 回传数据的Intent
     */
    protected final void setResultOK(Intent intent) {
        if (getActivity() != null) {
            getActivity().setResult(Activity.RESULT_OK, intent);
        }
    }

    /**
     * 显示Dialog
     *
     * @param dialogFragment IBaseDialogFragment
     */
    public final void showDialog(IBaseDialogFragment dialogFragment) {

        try {
            String tag = dialogFragment.getClass().getSimpleName();

            FragmentTransaction ft = mFragmentManager.beginTransaction();
            Fragment prev = getFragmentManager().findFragmentByTag(tag);
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);

            // 检查 是否isAdded()
            if (prev == null || (
                    !prev.isAdded() &&
                            !prev.isVisible() &&
                            !prev.isRemoving())) {
                dialogFragment.show(ft, tag);
            }

        } catch (IllegalStateException e) {
            LogUtils.wtf(e);
        }
    }


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

        mMainHandler = new MainHandler(new WeakReference<>(this));
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

    /**
     * 全局静态的handler
     */
    protected static final class MainHandler extends Handler {

        private final WeakReference<IBaseFragment> mOuterContext;

        public MainHandler(WeakReference<IBaseFragment> context) {
            this.mOuterContext = context;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            IBaseFragment fragment = mOuterContext.get();
            if (fragment != null) {
                fragment.handleMainMessage(msg);
            }

        }

        protected void onDestroy() {
            if (mOuterContext != null) {
                IBaseFragment fragment = mOuterContext.get();
                fragment = null;
                mOuterContext.clear();
            }
        }
    }
}
