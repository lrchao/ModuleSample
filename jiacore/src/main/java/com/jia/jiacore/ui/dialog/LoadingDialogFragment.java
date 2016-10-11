package com.jia.jiacore.ui.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jia.jiacore.R;
import com.jia.jiacore.constant.IBaseBundleKey;


/**
 * Description: 显示加载中的Dialog
 * 1.中间Progress
 * 2.下面文字描述
 *
 * @author lrc19860926@gmail.com
 * @date 16/3/21 下午1:39
 */
public class LoadingDialogFragment extends IBaseDialogFragment {

    public TextView mTvMessage;
    /**
     * Dialog 显示的文本
     */
    private String mMessage;

    /**
     * 是否可以cancel
     */
    private boolean mIsCancelable;

    public static LoadingDialogFragment newInstance(String message, boolean cancelable) {
        LoadingDialogFragment f = new LoadingDialogFragment();
        Bundle args = new Bundle();
        args.putString(IBaseBundleKey.INTENT_EXTRA_DIALOG_MESSAGE, message);
        args.putBoolean(IBaseBundleKey.INTENT_EXTRA_DIALOG_CANCELABLE, cancelable);
        f.setArguments(args);
        return f;
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.dialog_loading;
    }

    @Override
    protected void initView(View parentView) {

        mTvMessage = (TextView) parentView.findViewById(R.id.tv_dialog_message);
        mTvMessage.setText(mMessage);
        setCancelable(mIsCancelable);
    }

    @Override
    protected void initData(Bundle bundle) {
        mMessage = bundle.getString(IBaseBundleKey.INTENT_EXTRA_DIALOG_MESSAGE);
        mIsCancelable = bundle.getBoolean(IBaseBundleKey.INTENT_EXTRA_DIALOG_CANCELABLE, true);
    }

}
