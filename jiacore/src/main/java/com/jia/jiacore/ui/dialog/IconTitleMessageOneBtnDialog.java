package com.jia.jiacore.ui.dialog;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jia.jiacore.R;
import com.jia.jiacore.constant.IBaseBundleKey;
import com.jia.jiacore.ui.dialog.listener.DialogOneBtnClickListener;
import com.jia.jiacore.util.ResourceUtils;


/**
 * Description:
 * 1.有图标
 * 2.确认按钮
 * 3.Title
 * 4.Message
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/1 下午1:48
 */
public class IconTitleMessageOneBtnDialog extends IBaseDialogFragment implements View.OnClickListener {

    /**
     * 标题
     */
    private CharSequence mTitle;

    /**
     * 消息
     */
    private CharSequence mMessage;

    /**
     * 图标
     */
    private int mIcon;

    /**
     * 确认按钮的文本, 默认值R.string.dialog_got_it
     */
    private CharSequence mMiddleBtnText;

    private DialogOneBtnClickListener mListener;

    public static IconTitleMessageOneBtnDialog newInstance(CharSequence title,
                                                           CharSequence message,
                                                           @DrawableRes int iconId) {
        return newInstance(title, message, iconId, ResourceUtils.getString(R.string.dialog_got_it));
    }

    public static IconTitleMessageOneBtnDialog newInstance(CharSequence title,
                                                           CharSequence message,
                                                           @DrawableRes int iconId,
                                                           CharSequence middleBtnText) {
        IconTitleMessageOneBtnDialog f = new IconTitleMessageOneBtnDialog();
        Bundle args = new Bundle();
        args.putCharSequence(IBaseBundleKey.INTENT_EXTRA_DIALOG_TITLE, title);
        args.putCharSequence(IBaseBundleKey.INTENT_EXTRA_DIALOG_MESSAGE, message);
        args.putInt(IBaseBundleKey.INTENT_EXTRA_DIALOG_ICON, iconId);
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
        mIcon = bundle.getInt(IBaseBundleKey.INTENT_EXTRA_DIALOG_ICON);
        mMiddleBtnText = bundle.getCharSequence(IBaseBundleKey.INTENT_EXTRA_DIALOG_MIDDLE_BTN_TEXT);
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.dialog_icon_title_msg_one_btn;
    }

    @Override
    protected void initView(View parentView) {

        ImageView ivIcon = (ImageView) parentView.findViewById(R.id.iv_dialog_icon);
        TextView tvTitle = (TextView) parentView.findViewById(R.id.tv_dialog_title);
        TextView tvMessage = (TextView) parentView.findViewById(R.id.tv_dialog_message);
        Button btnMiddle = (Button) parentView.findViewById(R.id.btn_dialog_middle);
        btnMiddle.setOnClickListener(this);

        ivIcon.setImageResource(mIcon);
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
