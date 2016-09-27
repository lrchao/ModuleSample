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
 * 1.确认按钮， 默认值 “知道了”
 * 2.Title，
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/1 下午1:48
 */
public class TitleOneBtnDialog extends IBaseDialogFragment implements View.OnClickListener {

    /**
     * 标题
     */
    private CharSequence mTitle;

    /**
     * 确认按钮的文本, 默认：R.string.dialog_got_it
     */
    private CharSequence mMiddleBtnText;

    private DialogOneBtnClickListener mListener;

    public static TitleOneBtnDialog newInstance(CharSequence title) {
        return newInstance(title, ResourceUtils.getString(R.string.dialog_got_it));
    }

    public static TitleOneBtnDialog newInstance(CharSequence title,
                                                CharSequence middleBtnText) {
        TitleOneBtnDialog f = new TitleOneBtnDialog();
        Bundle args = new Bundle();
        args.putCharSequence(IBaseBundleKey.INTENT_EXTRA_DIALOG_TITLE, title);
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
        mMiddleBtnText = bundle.getCharSequence(IBaseBundleKey.INTENT_EXTRA_DIALOG_MIDDLE_BTN_TEXT);
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.dialog_title_one_btn;
    }

    @Override
    protected void initView(View parentView) {

        TextView tvTitle = (TextView) parentView.findViewById(R.id.tv_dialog_title);
        Button btnMiddle = (Button) parentView.findViewById(R.id.btn_dialog_middle);
        btnMiddle.setOnClickListener(this);

        tvTitle.setText(mTitle);
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
