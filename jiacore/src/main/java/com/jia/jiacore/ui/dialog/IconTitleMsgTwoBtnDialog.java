package com.jia.jiacore.ui.dialog;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jia.lib.R;
import com.jia.lib.constant.IBundleKey;
import com.jia.lib.ui.dialog.listener.DialogTwoBtnClickListener;

/**
 * Description:
 * 1.有图标, drawable resource id
 * 2.确认按钮
 * 3.Title
 * 4.Message
 *
 * @author liuranchao
 * @date 16/5/18 上午9:24
 */
public class IconTitleMsgTwoBtnDialog extends IDialogFragment implements View.OnClickListener {

    private CharSequence mTitle;

    private CharSequence mMessage;

    private int mIconResId;

    private CharSequence mLeftBtnText;

    private CharSequence mRightBtnText;

    private DialogTwoBtnClickListener mListener;

    public static IconTitleMsgTwoBtnDialog newInstance(CharSequence title,
                                                       CharSequence msg,
                                                       @DrawableRes int iconResId,
                                                       CharSequence leftBtnText,
                                                       CharSequence rightBtnText) {
        IconTitleMsgTwoBtnDialog f = new IconTitleMsgTwoBtnDialog();
        Bundle args = new Bundle();
        args.putCharSequence(IBundleKey.INTENT_EXTRA_DIALOG_TITLE, title);
        args.putCharSequence(IBundleKey.INTENT_EXTRA_DIALOG_MESSAGE, msg);
        args.putInt(IBundleKey.INTENT_EXTRA_DIALOG_ICON, iconResId);
        args.putCharSequence(IBundleKey.INTENT_EXTRA_DIALOG_LEFT_BTN_TEXT, leftBtnText);
        args.putCharSequence(IBundleKey.INTENT_EXTRA_DIALOG_RIGHT_BTN_TEXT, rightBtnText);
        f.setArguments(args);
        return f;
    }

    public void setListener(DialogTwoBtnClickListener listener) {
        mListener = listener;
    }

    @Override
    protected void initArgumentsData(Bundle bundle) {
        mTitle = bundle.getCharSequence(IBundleKey.INTENT_EXTRA_DIALOG_TITLE);
        mMessage = bundle.getCharSequence(IBundleKey.INTENT_EXTRA_DIALOG_MESSAGE);
        mIconResId = bundle.getInt(IBundleKey.INTENT_EXTRA_DIALOG_ICON);
        mLeftBtnText = bundle.getCharSequence(IBundleKey.INTENT_EXTRA_DIALOG_LEFT_BTN_TEXT);
        mRightBtnText = bundle.getCharSequence(IBundleKey.INTENT_EXTRA_DIALOG_RIGHT_BTN_TEXT);
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.dialog_icon_title_msg_two_btn;
    }

    @Override
    protected void initView(View parentView) {
        ImageView ivIcon = (ImageView) parentView.findViewById(R.id.iv_dialog_icon);
        TextView tvTitle = (TextView) parentView.findViewById(R.id.tv_dialog_title);
        TextView tvMsg = (TextView) parentView.findViewById(R.id.tv_dialog_message);
        Button btnRight = (Button) parentView.findViewById(R.id.btn_dialog_right);
        Button btnLeft = (Button) parentView.findViewById(R.id.btn_dialog_left);
        btnRight.setOnClickListener(this);
        btnLeft.setOnClickListener(this);

        if (mIconResId > 0) {
            ivIcon.setImageResource(mIconResId);
        }

        if (!TextUtils.isEmpty(mTitle)) {
            tvTitle.setText(mTitle);
        }

        if (!TextUtils.isEmpty(mMessage)) {
            tvMsg.setText(mMessage);
        }

        if (!TextUtils.isEmpty(mRightBtnText)) {
            btnRight.setText(mRightBtnText);
        }

        if (!TextUtils.isEmpty(mLeftBtnText)) {
            btnLeft.setText(mLeftBtnText);
        }

    }

    @Override
    public void onClick(View v) {
        dismissDialog();

        if (mListener != null) {
            int id = v.getId();
            if (id == R.id.btn_dialog_left) {
                mListener.onClickDialogLeftBtn();
            } else {
                mListener.onClickDialogRightBtn();
            }
        }

    }
}
