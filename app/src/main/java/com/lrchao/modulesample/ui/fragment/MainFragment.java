package com.lrchao.modulesample.ui.fragment;

import android.view.View;

import com.jia.jiacore.ui.fragment.IBaseFragment;
import com.jia.jiacore.util.DeviceUtils;
import com.jia.jiacore.util.LogUtils;
import com.lrchao.modulesample.R;

/**
 * Description: TODO
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/26 下午1:57
 */

public class MainFragment extends IBaseFragment {

    @Override
    protected int getLayoutViewId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View parentView) {

        LogUtils.e("DeviceUtils.getScreenWH()==" + DeviceUtils.getScreenWH());
        LogUtils.e("DeviceUtils.getSerialNumber()==" + DeviceUtils.getSerialNumber());
        LogUtils.e("DeviceUtils.getAndroidId(getContext())==" + DeviceUtils.getAndroidId(getContext()));
    }

    public static IBaseFragment getInstance() {
        return new MainFragment();
    }


}
