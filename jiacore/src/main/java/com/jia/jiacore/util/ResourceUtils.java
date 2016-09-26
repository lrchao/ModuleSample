package com.jia.jiacore.util;


import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.jia.jiacore.IBaseApplication;

/**
 * Description: 操作资源文件的工具类
 *
 * @author lrc19860926@gmail.com
 * @date 16/3/13 上午10:54
 */
public final class ResourceUtils {

    private ResourceUtils() {
    }

    /**
     * 获取arrays.xml中的字符串数组
     *
     * @param arrId Resource id  R.array.xxx
     * @return string数组
     */
    public static String[] getStringArray(@ArrayRes int arrId) {
        return IBaseApplication.getApplication().getResources().getStringArray(arrId);
    }

    /**
     * 获取arrays.xml中的int数组
     *
     * @param arrId Resource id  R.array.xxx
     * @return int数组
     */
    public static int[] getIntArray(@ArrayRes int arrId) {
        return IBaseApplication.getApplication().getResources().getIntArray(arrId);
    }

    /**
     * 获取strings.xml的文本
     *
     * @param resId      strings.xml
     * @param formatArgs 替换的参数
     * @return String
     */
    public static String getString(@StringRes int resId, Object... formatArgs) {
        return IBaseApplication.getApplication().getString(resId, formatArgs);
    }

    /**
     * 获取color 颜色
     *
     * @param colorId colors.xml
     * @return color
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static int getColor(@ColorRes int colorId) {
        if (Utils.hasM()) {
            return IBaseApplication.getApplication().getResources().getColor(colorId, null);
        } else {
            return IBaseApplication.getApplication().getResources().getColor(colorId);
        }
    }

    public static float getDimen(@DimenRes int dimenId) {
        return IBaseApplication.getApplication().getResources().getDimensionPixelSize(dimenId);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static Drawable getDrawable(@DrawableRes int drawableId) {
        if (Utils.hasLollipop()) {
            return IBaseApplication.getApplication().getResources().getDrawable(drawableId, IBaseApplication.getApplication().getTheme());
        } else {
            return IBaseApplication.getApplication().getResources().getDrawable(drawableId);
        }

    }
}
