package com.lrchao.modulesample.ui.fragment;

import android.view.View;

import com.jia.jiacore.mvp.IBasePresenter;
import com.jia.jiacore.network.IBaseRequest;
import com.jia.jiacore.ui.fragment.IBaseFragment;
import com.jia.jiacore.ui.fragment.IBaseMvpFragment;
import com.jia.jiacore.util.LogUtils;
import com.lrchao.modulesample.R;
import com.lrchao.modulesample.mvp.homepage.HomepageContract;
import com.lrchao.modulesample.mvp.homepage.HomepagePresenter;

/**
 * Description: 页面加载的Fragment
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/26 下午1:56
 */

public class PageNetworkFragment extends IBaseMvpFragment implements
        View.OnClickListener, HomepageContract.View {

    private HomepagePresenter mHomepagePresenter;

    @Override
    protected IBasePresenter createPresenter() {
        mHomepagePresenter = new HomepagePresenter();
        return mHomepagePresenter;
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

                mHomepagePresenter.loadPageData();

                break;
            default:
                break;
        }
    }

    public void onSuccess(IBaseRequest request, Object resultObj) {

    }

    public void onFailed() {

    }


    @Override
    public void showPageData() {
        LogUtils.e("showPageData()");
    }
}
