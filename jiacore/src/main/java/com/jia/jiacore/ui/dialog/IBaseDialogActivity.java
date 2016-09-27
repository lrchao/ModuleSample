package com.jia.jiacore.ui.dialog;

import com.jia.jiacore.R;
import com.jia.jiacore.ui.activity.IBaseActivity;

/**
 * Description: 显示DialogFragment的Activity
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/27 下午3:02
 */

public abstract class IBaseDialogActivity extends IBaseActivity {

    @Override
    protected int getLayoutViewId() {
        return R.layout.activity_base_dialog;
    }

    protected abstract IBaseDialogFragment getDialogFragment();

    @Override
    protected void initView() {
        showFragment(getDialogFragment());
    }
}
