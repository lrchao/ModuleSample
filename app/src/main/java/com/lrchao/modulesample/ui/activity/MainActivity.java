package com.lrchao.modulesample.ui.activity;

import android.view.View;

import com.jia.jiacore.ui.IBaseActivity;
import com.lrchao.modulesample.R;

public class MainActivity extends IBaseActivity implements View.OnClickListener {

    @Override
    protected int getLayoutViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        findViewById(R.id.btn_page_network).setOnClickListener(this);
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
