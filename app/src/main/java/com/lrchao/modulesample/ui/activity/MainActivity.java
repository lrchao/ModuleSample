package com.lrchao.modulesample.ui.activity;

import android.view.View;

import com.jia.jiacore.ui.activity.IBaseActivity;
import com.lrchao.modulesample.R;
import com.lrchao.modulesample.ui.fragment.MainFragment;

public class MainActivity extends IBaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutViewId() {
        return R.layout.activity_common;
    }

    @Override
    protected void initView() {
        showFragment(MainFragment.getInstance());
        //findViewById(R.id.btn_page_network).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_page_network:
                break;
            default:
                break;
        }
    }
}