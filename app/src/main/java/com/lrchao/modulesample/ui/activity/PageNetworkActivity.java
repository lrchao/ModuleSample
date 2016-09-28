package com.lrchao.modulesample.ui.activity;

import com.jia.jiacore.ui.activity.IBaseActivity;
import com.lrchao.modulesample.R;
import com.lrchao.modulesample.ui.fragment.PageNetworkFragment;

/**
 * Description: 页面数据网络加载的页面
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/26 上午11:29
 */

public class PageNetworkActivity extends IBaseActivity {


    @Override
    protected int getLayoutViewId() {
        return R.layout.activity_common;
    }

    @Override
    protected void initView() {
        showFragment(PageNetworkFragment.getInstance());
    }
}
