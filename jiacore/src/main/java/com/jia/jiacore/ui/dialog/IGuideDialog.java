package com.jia.jiacore.ui.dialog;

import android.support.annotation.CallSuper;
import android.view.View;

/**
 * Description: 引导图的dialog的基类
 *
 * @author liuranchao
 * @date 16/7/15 下午4:46
 */
public abstract class IGuideDialog extends IBaseDialogFragment {

    @CallSuper
    @Override
    protected void initView(View parentView) {
        parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissDialog();
            }
        });
    }
}
