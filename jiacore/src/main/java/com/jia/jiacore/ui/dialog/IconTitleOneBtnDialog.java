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
 * 1.有图标  默认：R.drawable.ic_smile
 * 2.确认按钮 默认：R.string.dialog_got_it
 * 3.Title
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/1 下午1:48
 */
public class IconTitleOneBtnDialog extends IBaseDialogFragment implements View.OnClickListener {

    /**
     * 标题
     */
    private CharSequence mTitle;

    /**
     * 图标
     */
    private int mIcon;

    /**
     * 确认按钮的文本
     */
    private CharSequence mMiddleBtnText;

    private DialogOneBtnClickListener mListener;

    public static IconTitleOneBtnDialog newInstance(CharSequence title) {
        return newInstance(title, R.drawable.dialog_small_smile, ResourceUtils.getString(R.string.dialog_got_it));
    }

    public static IconTitleOneBtnDialog newInstance(CharSequence title,
                                                    @DrawableRes int iconId,
                                                    CharSequence middleBtnText) {
        IconTitleOneBtnDialog f = new IconTitleOneBtnDialog();
        Bundle args = new Bundle();
        args.putCharSequence(IBaseBundleKey.INTENT_EXTRA_DIALOG_TITLE, title);
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
        mIcon = bundle.getInt(IBaseBundleKey.INTENT_EXTRA_DIALOG_ICON);
        mMiddleBtnText = bundle.getCharSequence(IBaseBundleKey.INTENT_EXTRA_DIALOG_MIDDLE_BTN_TEXT);
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.dialog_icon_title_one_btn;
    }

    @Override
    protected void initView(View parentView) {

        ImageView ivIcon = (ImageView) parentView.findViewById(R.id.iv_dialog_icon);
        TextView tvTitle = (TextView) parentView.findViewById(R.id.tv_dialog_title);
        Button btnMiddle = (Button) parentView.findViewById(R.id.btn_dialog_middle);
        btnMiddle.setOnClickListener(this);

        ivIcon.setImageResource(mIcon);
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
