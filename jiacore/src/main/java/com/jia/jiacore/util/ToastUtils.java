package com.jia.jiacore.util;

import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;

import com.jia.jiacore.IBaseApplication;

/**
 * Description: Toast帮助类
 *
 * @author lrc19860926@gmail.com
 * @date 16/3/15 下午2:51
 */
public final class ToastUtils {

    private ToastUtils() {
    }

    /**
     * 显示系统的toast
     *
     * @param resId strings.xml
     */
    public static void show(@StringRes int resId) {
        Toast.makeText(IBaseApplication.getApplication(), resId, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示系统的toast
     *
     * @param text CharSequence
     */
    public static void show(CharSequence text) {
        if (!TextUtils.isEmpty(text)) {
            Toast.makeText(IBaseApplication.getApplication(), text, Toast.LENGTH_LONG).show();
        }
    }
}
