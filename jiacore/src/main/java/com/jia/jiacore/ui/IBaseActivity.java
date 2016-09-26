package com.jia.jiacore.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;

import com.jia.jiacore.CoreBuildConfig;
import com.jia.jiacore.R;
import com.jia.jiacore.util.Utils;

import java.util.List;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


/**
 * Description: Activity基类
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/26 上午11:12
 */
public abstract class IBaseActivity extends SwipeBackActivity {

    /**
     * 输出日志的TAG
     */
    protected String mTag = this.getClass().getSimpleName();

    /**
     * FragmentManager
     */
    protected FragmentManager mFragmentManager;

    /**
     * 当前Activity的window
     */
    private Window mWindow;

    //====================
    // 子类可调用的方法
    //====================

    /**
     * fragment 切换
     *
     * @param from 当前是哪个fragment
     * @param to   要跳转到哪个fragment
     */
    protected final void switchContent(Fragment from, Fragment to) {
        switchContent(from, to, null);
    }

    /**
     * fragment 切换
     * 布局中必须有R.id.fragment_container的ViewGroup
     *
     * @param from   当前是哪个fragment
     * @param to     要跳转到哪个fragment
     * @param bundle 携带的数据
     */
    protected final void switchContent(Fragment from, Fragment to, Bundle bundle) {

        if (to != null) {
            //TODO
            //SessionManager.getInstance().setCurrentPageName(to.getClass().getSimpleName());
        }

        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        // 如果当前的fragment为null,则直接显示to fragment
        if (from == null) {
            showFragment(to);
            return;
        }

        // 如果from已经添加 则隐藏
        if (from.isAdded()) {
            transaction.hide(from);
        }
        // 如果to 已经添加 则显示
        if (to.isAdded()) {
            transaction.show(to);
        } else {
            // 如果TO未添加则添加
            to.setArguments(bundle);
            transaction.add(R.id.fragment_container, to);
        }
        if (!mFragmentManager.isDestroyed()) {
            transaction.commitAllowingStateLoss();
        }
    }

    /**
     * @author Kevin Liu
     * 显示Fragment
     */
    protected final void showFragment(Fragment fragment) {

        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        transaction.replace(R.id.fragment_container, fragment);

        if (!mFragmentManager.isDestroyed() && !fragment.isAdded()) {
            transaction.commitAllowingStateLoss();
        }
    }


    //====================
    // 子类必须继承的方法
    //====================

    /**
     * 子类设置加载的View
     *
     * @return layout view
     */
    protected abstract int getLayoutViewId();


    //====================
    // 子类可重写的方法
    //====================

    /**
     * 初始化数据
     *
     * @param intent Intent
     */
    protected void initData(Intent intent) {
    }

    /**
     * 初始化View的方法
     */
    protected void initView() {
    }

    /**
     * Activity的方向
     */
    protected int getScreenOrientation() {
        return ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    /**
     * 是否设置为透明system bar
     */
    protected boolean isSystemBarTransparent() {
        return true;
    }

    /**
     * 延迟加载，等UI初始化好后调用
     */
    protected void onDelayLoad() {

    }

    //====================
    // 私有的方法
    //====================

    @SuppressWarnings({"InlinedApi", "WrongConstant"})
    @CallSuper
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        if (CoreBuildConfig.DEBUG) {
            Utils.enableStrictMode(this.getClass());
        }
        super.onCreate(savedInstanceState);
        // 设置页面方向
        setRequestedOrientation(getScreenOrientation());
        setContentView(getLayoutViewId());
        mFragmentManager = getSupportFragmentManager();
        initData(getIntent());

        mWindow = getWindow();

        // 设置透明system bar
        if (isSystemBarTransparent()) {
            if (Utils.hasLollipop()) {
                mWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            }

            if (Utils.hasKITKAT()) {
                mWindow.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }

        initView();

        // 用于延迟加载
        mWindow.getDecorView().post(new Runnable() {
            @Override
            public void run() {
                onDelayLoad();
            }
        });
    }

    @CallSuper
    @Override
    protected final void onStart() {
        super.onStart();
    }

    @CallSuper
    @Override
    protected final void onResume() {
        super.onResume();
    }

    @CallSuper
    @Override
    protected final void onPause() {
        super.onPause();
    }

    @CallSuper
    @Override
    protected final void onStop() {
        super.onStop();
    }

    @CallSuper
    @Override
    protected final void onDestroy() {
        super.onDestroy();
    }

    /**
     * for fix  java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
     * 这个没效果
     */
    /**
     * ava.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
     * at android.support.v4.app.FragmentManagerImpl.checkStateLoss(FragmentManager.java:1489)
     * at android.support.v4.app.FragmentManagerImpl.enqueueAction(FragmentManager.java:1507)
     * at android.support.v4.app.BackStackRecord.commitInternal(BackStackRecord.java:634)
     * at android.support.v4.app.BackStackRecord.commit(BackStackRecord.java:613)
     * at android.support.v4.app.FragmentTabHost.onAttachedToWindow(FragmentTabHost.java:282)
     * at android.view.View.dispatchAttachedToWindow(View.java:13525)
     * at android.view.ViewGroup.dispatchAttachedToWindow(ViewGroup.java:2688)
     * at android.view.ViewGroup.dispatchAttachedToWindow(ViewGroup.java:2695)
     * at android.view.ViewGroup.dispatchAttachedToWindow(ViewGroup.java:2695)
     * at android.view.ViewGroup.dispatchAttachedToWindow(ViewGroup.java:2695)
     * at android.view.ViewGroup.dispatchAttachedToWindow(ViewGroup.java:2695)
     * at android.view.ViewGroup.dispatchAttachedToWindow(ViewGroup.java:2695)
     * at android.view.ViewGroup.dispatchAttachedToWindow(ViewGroup.java:2695)
     * at android.view.ViewRootImpl.performTraversals(ViewRootImpl.java:1299)
     * at android.view.ViewRootImpl.doTraversal(ViewRootImpl.java:1061)
     * at android.view.ViewRootImpl$TraversalRunnable.run(ViewRootImpl.java:5885)
     * at android.view.Choreographer$CallbackRecord.run(Choreographer.java:767)
     * at android.view.Choreographer.doCallbacks(Choreographer.java:580)
     * at android.view.Choreographer.doFrame(Choreographer.java:550)
     * at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:753)
     * at android.os.Handler.handleCallback(Handler.java:739)
     * at android.os.Handler.dispatchMessage(Handler.java:95)
     * at android.os.Looper.loop(Looper.java:135)
     * at android.app.ActivityThread.main(ActivityThread.java:5254)
     * at java.lang.reflect.Method.invoke(Native Method)
     * at java.lang.reflect.Method.invoke(Method.java:372)
     * at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:903)
     * at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:698)
     */
    @CallSuper
    @Override
    protected void onSaveInstanceState(Bundle outState) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragmentList = mFragmentManager.getFragments();
        if (fragmentList != null && fragmentList.size() > 0) {
            for (Fragment fragment : fragmentList) {
                if (fragment != null) {
                    fragment.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragmentList = mFragmentManager.getFragments();
        if (fragmentList != null && fragmentList.size() > 0) {
            for (Fragment fragment : fragmentList) {
                if (fragment != null) {
                    fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
                }
            }
        }
    }

}
