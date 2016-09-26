package com.jia.jiacore.util;

import android.content.Context;

import com.jia.jiacore.IBaseApplication;

/**
 * Description: 单位转换的工具类
 *
 * @author lrc19860926@gmail.com
 * @date 16/3/17 上午10:56
 */
public final class DensityUtils {

    /**
     * dp to px
     */
    private static final float RATIO = 0.5f;

    private DensityUtils() {

    }

    /**
     * Description: 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param dpValue dp
     * @return px
     */
    public static int dip2px(float dpValue) {
        final float scale = getDensity(IBaseApplication.getApplication());
        return (int) (dpValue * scale + RATIO);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(float spValue) {
        final float fontScale = getScaleDensity(IBaseApplication.getApplication());
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * Description: 获取当前设备的像素
     *
     * @param context Context
     *                0.75 - ldpi
     *                1.0 - mdpi
     *                1.5 - hdpi
     *                2.0 - xhdpi
     *                3.0 - xxhdpi
     *                4.0 - xxxhdpi
     */
    public static float getDensity(Context context) {
        return context.getResources()
                .getDisplayMetrics().density;
    }

    public static float getScaleDensity(Context context) {
        return context.getResources().getDisplayMetrics().scaledDensity;
    }

}
