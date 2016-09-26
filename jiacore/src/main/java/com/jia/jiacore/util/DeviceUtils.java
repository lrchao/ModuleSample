package com.jia.jiacore.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;

import com.jia.jiacore.IBaseApplication;

/**
 * Description: 设备硬件相关的工具类
 *
 * @author lrc19860926@gmail.com
 * @date 16/3/17 上午9:42
 */
public final class DeviceUtils {

    private DeviceUtils() {
    }

    /**
     * 获取屏幕宽高
     *
     * @return Point x: width y : height
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static Point getScreenWH() {
        WindowManager windowManager = (WindowManager) IBaseApplication.getApplication()
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point info = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            display.getSize(info);
        } else {
            info = new Point(display.getWidth(), display.getHeight());
        }
        return info;
    }
}
