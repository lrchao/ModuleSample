package com.jia.jiacore.ui.dialog;


import com.jia.jiacore.R;

/**
 * Description: Menu Dialog的基类
 *
 * @author lrc19860926@gmail.com
 * @date 16/4/26 上午11:10
 */
public abstract class IMenuDialogFragment extends IBaseDialogFragment {

    @Override
    protected boolean isBottom() {
        return true;
    }

    @Override
    protected int getAnimation() {
        return R.style.DialogMenuAnimation;
    }
}
