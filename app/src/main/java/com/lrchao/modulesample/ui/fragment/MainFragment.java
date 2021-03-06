package com.lrchao.modulesample.ui.fragment;

import android.content.Intent;
import android.view.View;

import com.jia.jiacore.manager.shared_preference.SharedPreferenceItem;
import com.jia.jiacore.ui.fragment.IBaseFragment;
import com.lrchao.modulesample.R;
import com.lrchao.modulesample.ui.activity.PageNetworkActivity;

/**
 * Description: 主页面
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/26 下午1:57
 */

public class MainFragment extends IBaseFragment implements View.OnClickListener {

    public static final SharedPreferenceItem PREF_HOME_REQUIREMENT_FINISH_FLAG =
            new SharedPreferenceItem("pref.home_requirement.finish.flag", String.class);

    public static IBaseFragment getInstance() {
        return new MainFragment();
    }

    @Override
    protected int getLayoutViewId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View parentView) {
        parentView.findViewById(R.id.btn_page_network).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_page_network:
                startActivity(new Intent(getActivity(), PageNetworkActivity.class));
                break;
            default:
                break;
        }
    }
}
