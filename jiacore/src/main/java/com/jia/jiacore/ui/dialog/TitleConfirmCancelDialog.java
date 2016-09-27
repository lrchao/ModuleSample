package com.jia.jiacore.ui.dialog;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jia.jiacore.R;
import com.jia.jiacore.constant.IBaseBundleKey;
import com.jia.jiacore.util.ResourceUtils;


/**
 * Description: 两个按钮的Dialog
 * 1.中间一个message
 * 2左边取消,默认黄字、白色背景、黄色边框、取消
 * 3.右边确认，默认白字、黄色背景、黄色边框、确认
 * 4.白色背景
 *
 * @author liuranchao
 * @date 16/4/11 下午7:31
 */
public class TitleConfirmCancelDialog extends IBaseDialogFragment implements View.OnClickListener {

    public TextView mTvTitle;

    public Button mBtnCancel;

    public Button mBtnConfirm;

    /**
     * 内容
     */
    private String mContent;

    /**
     * 取消按钮文本
     */
    private String mCancelText = ResourceUtils.getString(R.string.dialog_cancel);

    /**
     * 确认按钮文本
     */
    private String mConfirmText = ResourceUtils.getString(R.string.dialog_confirm);

    private OnConfirmCancelClickListener mOnConfirmCancelClickListener;

    public static TitleConfirmCancelDialog newInstance(String message, String btnConfirmText,
                                                       String btnCancelText) {
        TitleConfirmCancelDialog f = new TitleConfirmCancelDialog();
        Bundle args = new Bundle();
        args.putString(IBaseBundleKey.INTENT_EXTRA_DIALOG_MESSAGE, message);
        args.putString(IBaseBundleKey.INTENT_EXTRA_DIALOG_RIGHT_BTN_TEXT, btnConfirmText);
        args.putString(IBaseBundleKey.INTENT_EXTRA_DIALOG_LEFT_BTN_TEXT, btnCancelText);
        f.setArguments(args);
        return f;
    }

    public void setOnConfirmCancelClickListener(OnConfirmCancelClickListener onConfirmCancelClickListener) {
        mOnConfirmCancelClickListener = onConfirmCancelClickListener;
    }

    @Override
    protected void initData(Bundle bundle) {
        mContent = bundle.getString(IBaseBundleKey.INTENT_EXTRA_DIALOG_MESSAGE);
        String cancel = bundle.getString(IBaseBundleKey.INTENT_EXTRA_DIALOG_LEFT_BTN_TEXT);
        if (!TextUtils.isEmpty(cancel)) {
            mCancelText = cancel;
        }
        String confirm = bundle.getString(IBaseBundleKey.INTENT_EXTRA_DIALOG_RIGHT_BTN_TEXT);
        if (!TextUtils.isEmpty(confirm)) {
            mConfirmText = confirm;
        }
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.dialog_title_confirm_cancel;
    }

    @Override
    protected void initView(View parentView) {

        mTvTitle = (TextView) parentView.findViewById(R.id.tv_dialog_title);
        mBtnCancel = (Button) parentView.findViewById(R.id.btn_dialog_left);
        mBtnCancel.setOnClickListener(this);
        mBtnConfirm = (Button) parentView.findViewById(R.id.btn_dialog_right);

        mTvTitle.setText(Html.fromHtml(mContent));
        mBtnCancel.setText(mCancelText);
        mBtnConfirm.setText(mConfirmText);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_dialog_left) {
            onClickCancel();
        } else if (i == R.id.btn_dialog_right) {
            onClickConfirm();
        }
    }

    public void onClickConfirm() {
        dismissDialog();
        if (mOnConfirmCancelClickListener != null) {
            mOnConfirmCancelClickListener.onClickDialogConfirm();
        }
    }

    public void onClickCancel() {
        dismissDialog();
        if (mOnConfirmCancelClickListener != null) {
            mOnConfirmCancelClickListener.onClickDialogCancel();
        }
    }


    /**
     * 点击确认和取消的监听
     */
    public interface OnConfirmCancelClickListener {
        /**
         * 点击取消
         */
        void onClickDialogCancel();

        /**
         * 点击确认
         */
        void onClickDialogConfirm();
    }
}
