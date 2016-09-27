package com.lrchao.modulesample.ui.activity;

import com.jia.jiacore.ui.activity.IBaseActivity;
import com.lrchao.modulesample.R;
import com.lrchao.modulesample.ui.fragment.MainFragment;

public class MainActivity extends IBaseActivity {

    @Override
    protected int getLayoutViewId() {
        return R.layout.activity_common;
    }

    @Override
    protected void initView() {
        showFragment(MainFragment.getInstance());
    }
}
