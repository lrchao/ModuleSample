package com.lrchao.modulesample.ui.fragment;

import android.view.View;

import com.jia.jiacore.manager.shared_preference.SharedPreferenceItem;
import com.jia.jiacore.ui.dialog.IconTitleMsgTwoBtnDialog;
import com.jia.jiacore.ui.fragment.IBaseFragment;
import com.lrchao.modulesample.R;

/**
 * Description: TODO
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/26 下午1:57
 */

public class MainFragment extends IBaseFragment implements View.OnClickListener {

    public static final SharedPreferenceItem PREF_HOME_REQUIREMENT_FINISH_FLAG =
            new SharedPreferenceItem("pref.home_requirement.finish.flag", String.class);

    @Override
    protected int getLayoutViewId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View parentView) {
        parentView.findViewById(R.id.btn_page_network).setOnClickListener(this);
    }

    public static IBaseFragment getInstance() {
        return new MainFragment();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_page_network:
                showDialog(IconTitleMsgTwoBtnDialog.newInstance("title", "message", R.drawable.dialog_small_smile, "确认", "取消"));
                break;
            default:
                break;
        }
    }
}
