package com.jia.jiacore.ui.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jia.jiacore.R;
import com.jia.jiacore.constant.IBaseBundleKey;
import com.jia.jiacore.ui.dialog.listener.DialogOneBtnClickListener;
import com.jia.jiacore.util.ResourceUtils;

/**
 * Description:
 * 2.确认按钮
 * 3.Title
 * 4.Message
 *
 * @author liuranchao
 * @date 16/9/1 下午1:48
 */
public class TitleMessageOneBtnDialog extends IBaseDialogFragment implements View.OnClickListener {

    /**
     * 标题
     */
    private CharSequence mTitle;

    /**
     * 消息
     */
    private CharSequence mMessage;

    /**
     * 确认按钮的文本, 默认值 R.string.dialog_got_it
     */
    private CharSequence mMiddleBtnText;

    private DialogOneBtnClickListener mListener;

    public static TitleMessageOneBtnDialog newInstance(CharSequence title,
                                                       CharSequence message) {
        return newInstance(title, message, ResourceUtils.getString(R.string.dialog_got_it));
    }

    public static TitleMessageOneBtnDialog newInstance(CharSequence title,
                                                       CharSequence message,
                                                       CharSequence middleBtnText) {
        TitleMessageOneBtnDialog f = new TitleMessageOneBtnDialog();
        Bundle args = new Bundle();
        args.putCharSequence(IBaseBundleKey.INTENT_EXTRA_DIALOG_TITLE, title);
        args.putCharSequence(IBaseBundleKey.INTENT_EXTRA_DIALOG_MESSAGE, message);
        args.putCharSequence(IBaseBundleKey.INTENT_EXTRA_DIALOG_MIDDLE_BTN_TEXT, middleBtnText);
        f.setArguments(args);
        return f;
    }

    public void setListener(DialogOneBtnClickListener listener) {
        mListener = listener;
    }

    @Override
    protected void initData(Bundle bundle) {
        mTitle = bundle.getCharSequence(IBaseBundleKey.INTENT_EXTRA_DIALOG_TITLE);
        mMessage = bundle.getCharSequence(IBaseBundleKey.INTENT_EXTRA_DIALOG_MESSAGE);
        mMiddleBtnText = bundle.getCharSequence(IBaseBundleKey.INTENT_EXTRA_DIALOG_MIDDLE_BTN_TEXT);
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.dialog_title_msg_one_btn;
    }

    @Override
    protected void initView(View parentView) {

        TextView tvTitle = (TextView) parentView.findViewById(R.id.tv_dialog_title);
        TextView tvMessage = (TextView) parentView.findViewById(R.id.tv_dialog_message);
        Button btnMiddle = (Button) parentView.findViewById(R.id.btn_dialog_middle);
        btnMiddle.setOnClickListener(this);

        tvTitle.setText(mTitle);
        tvMessage.setText(mMessage);
        btnMiddle.setText(mMiddleBtnText);
    }

    @Override
    public void onClick(View v) {

        dismissDialog();

        if (mListener != null) {
            if (v.getId() == R.id.btn_dialog_middle) {
                mListener.onClickDialogMiddleBtn();
            }
        }
    }
}
