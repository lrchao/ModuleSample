package com.jia.jiacore.ui.dialog;

import com.jia.jiacore.R;
import com.jia.jiacore.ui.activity.IBaseActivity;
import com.jia.jiacore.ui.dialog.listener.OnDialogDismissListener;

/**
 * Description: 显示DialogFragment的Activity
 * 1. 在Androidmanifest.xml <activity>中android:theme="@style/DialogTransparent"
 * @author lrc19860926@gmail.com
 * @date 16/9/27 下午3:02
 */

public abstract class IBaseDialogActivity extends IBaseActivity implements OnDialogDismissListener {

    @Override
    protected int getLayoutViewId() {
        return R.layout.activity_base_dialog;
    }

    protected abstract IBaseDialogFragment getDialogFragment();

    @Override
    protected void initView() {

        IBaseDialogFragment baseDialogFragment = getDialogFragment();
        if (baseDialogFragment == null) {
            finish();
        } else {
            baseDialogFragment.setOnDialogDismissListener(this);
            showFragment(baseDialogFragment);
        }

    }

    @Override
    public void onDialogDismissListener() {
        finish();
    }
}
