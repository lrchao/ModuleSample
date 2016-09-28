package com.lrchao.modulesample.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jia.jiacore.network.IBaseRequest;
import com.jia.jiacore.service.ApiService;
import com.jia.jiacore.ui.fragment.IBaseFragment;
import com.lrchao.modulesample.R;
import com.lrchao.modulesample.network.HomepageRequest;

/**
 * Description: 页面加载的Fragment
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/26 下午1:56
 */

public class PageNetworkFragment extends IBaseFragment implements View.OnClickListener {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.fragment_page_network;
    }

    @Override
    protected void initView(View parentView) {
        parentView.findViewById(R.id.btn_send_request).setOnClickListener(this);

    }

    public static IBaseFragment getInstance() {
        return new PageNetworkFragment();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_request:
                getActivity().startService(ApiService.createIntent(new HomepageRequest()));
                break;
            default:
                break;
        }
    }

    public void onSuccess(IBaseRequest request, Object resultObj) {

    }

    public void onFailed() {

    }


}
