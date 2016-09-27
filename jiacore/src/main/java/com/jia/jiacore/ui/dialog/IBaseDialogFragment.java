package com.jia.jiacore.ui.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.jia.jiacore.R;
import com.jia.jiacore.ui.dialog.listener.OnDialogDismissListener;
import com.jia.jiacore.util.LogUtils;

/**
 * Description: DialogFragment的基类
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/27 下午3:21
 */

public abstract class IBaseDialogFragment extends DialogFragment {

    /**
     * 对应的Fragment Name
     */
    protected String mTag = this.getClass().getSimpleName();

    private OnDialogDismissListener mOnDialogDismissListener;

    //========================================
    // 子类继承的
    //========================================

    /**
     * 设置初始化的layout view
     *
     * @return Layout View
     */
    protected abstract int getLayoutViewId();

    /**
     * 初始化view的方法
     */
    protected abstract void initView(View parentView);

    /**
     * 子类获取传递来的参数
     *
     * @param bundle Bundle
     */
    protected void initData(Bundle bundle) {

    }

    /**
     * 依赖注入初始化实现在此方法中
     */
    protected void initInjectionView(View view) {
    }

    /**
     * 设置是否显示在底部
     */
    protected boolean isBottom() {
        return false;
    }

    /**
     * 设置是否显示在顶部
     */
    protected boolean isTop() {
        return false;
    }

    /**
     * 设置DialogFragment的动画
     */
    protected int getAnimation() {
        return 0;
    }

    //========================================
    // 外部调用的
    //========================================

    /**
     * 设置消失监听
     *
     * @param onDialogDismissListener OnDialogDismissListener
     */
    public void setOnDialogDismissListener(OnDialogDismissListener onDialogDismissListener) {
        mOnDialogDismissListener = onDialogDismissListener;
    }

    /**
     * 消失Dialog
     */
    public void dismissDialog() {
        try {

            if (getActivity() != null && getActivity() instanceof IBaseDialogActivity) {
                if (mOnDialogDismissListener != null) {
                    mOnDialogDismissListener.onDialogDismissListener();
                }
            } else {
                dismiss();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //========================================
    // 内部处理
    //========================================

    @CallSuper
    @Override
    public final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.CustomDialog);
        initData(getArguments());
        LogUtils.d(mTag, "onCreate()");
    }

    @CallSuper
    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutViewId(), null);

        initInjectionView(view);

        initView(view);

        if (getDialog() != null && getDialog().getWindow() != null) {
            WindowManager.LayoutParams params = getDialog().getWindow()
                    .getAttributes();

            if (params != null) {
                if (isBottom()) {
                    params.gravity = Gravity.BOTTOM | Gravity.CENTER;
                }
                if (isTop()) {
                    params.gravity = Gravity.TOP;
                }

                getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                getDialog().getWindow().setAttributes(params);
            }

            // 设置动画
            if (getAnimation() > 0) {
                getDialog().getWindow()
                        .getAttributes().windowAnimations = getAnimation();
            }
        }
        return view;
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
    }

    @CallSuper
    @Override
    public void onPause() {
        super.onPause();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @CallSuper
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnDialogDismissListener != null) {
            mOnDialogDismissListener.onDialogDismissListener();
        }
    }

    /**
     * Dialog cancel时调用
     *
     * @param dialog DialogInterface
     */
    @CallSuper
    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

}
