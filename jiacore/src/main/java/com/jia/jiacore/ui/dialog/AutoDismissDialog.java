package com.jia.jiacore.ui.dialog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jia.jiacore.R;
import com.jia.jiacore.constant.IBaseBundleKey;

import java.lang.ref.WeakReference;

/**
 * Description:
 * 自动消失的dialog
 * 默认3000毫秒
 *
 * @author ouyangzx
 * @date 16/4/26 上午10:50
 */

public class AutoDismissDialog extends IBaseDialogFragment {

    private static final int MSG_WHAT_FINISH = 1; // 关闭页面

    private CharSequence mTitle;

    private int mIconResId;

    private long mDelayTime;

    public static AutoDismissDialog newInstance(CharSequence title) {
        return newInstance(title, R.drawable.dialog_big_smile, 3000);
    }

    public static AutoDismissDialog newInstance(CharSequence title,
                                                @DrawableRes int iconResId,
                                                long delayTime) {
        AutoDismissDialog f = new AutoDismissDialog();
        Bundle args = new Bundle();
        args.putCharSequence(IBaseBundleKey.INTENT_EXTRA_DIALOG_TITLE, title);
        args.putInt(IBaseBundleKey.INTENT_EXTRA_DIALOG_ICON, iconResId);
        args.putLong(IBaseBundleKey.INTENT_EXTRA_DIALOG_DELAY_DISMISS_TIME, delayTime);
        f.setArguments(args);
        return f;
    }

    @Override
    protected void initData(Bundle bundle) {
        mTitle = bundle.getCharSequence(IBaseBundleKey.INTENT_EXTRA_DIALOG_TITLE);
        mIconResId = bundle.getInt(IBaseBundleKey.INTENT_EXTRA_DIALOG_ICON);
        mDelayTime = bundle.getLong(IBaseBundleKey.INTENT_EXTRA_DIALOG_DELAY_DISMISS_TIME);
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.dialog_icon_title;
    }

    @Override
    protected void initView(View parentView) {
        new MyHandler(new WeakReference<>(this)).sendEmptyMessageDelayed(MSG_WHAT_FINISH, mDelayTime);
        ImageView ivIcon = (ImageView) parentView.findViewById(R.id.iv_dialog_icon);
        TextView tvTitle = (TextView) parentView.findViewById(R.id.tv_dialog_title);

        ivIcon.setImageResource(mIconResId);
        tvTitle.setText(mTitle);
    }


    private static class MyHandler extends Handler {

        private WeakReference<AutoDismissDialog> mReference;

        public MyHandler(WeakReference<AutoDismissDialog> reference) {
            mReference = reference;
        }

        @Override
        public void handleMessage(Message msg) {
            if (mReference.get() != null) {
                switch (msg.what) {
                    case MSG_WHAT_FINISH:
                        mReference.get().dismissDialog();
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
