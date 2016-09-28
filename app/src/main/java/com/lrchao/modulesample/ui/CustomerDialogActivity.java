package com.lrchao.modulesample.ui;

import com.jia.jiacore.ui.dialog.IBaseDialogActivity;
import com.jia.jiacore.ui.dialog.IBaseDialogFragment;
import com.jia.jiacore.ui.dialog.TitleOneBtnDialog;

/**
 * Description: 对话框的activity
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/27 下午5:43
 */

public class CustomerDialogActivity extends IBaseDialogActivity {

    @Override
    protected IBaseDialogFragment getDialogFragment() {
        return new TitleOneBtnDialog().newInstance("BBB");
    }
}
