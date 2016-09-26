package com.jia.jiacore.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Description: 处理时间，日期等工具类
 * "yyyy-MM-dd HH:mm:ss"
 *
 * @author liuranchao
 * @date 16/3/17 上午10:54
 */
public final class DateUtils {

    public static final String SD_YYYY_MM_DD_FORMATTER = "yyyy-MM-dd";
    public static final String SD_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd-HH-mm-ss";
    public static final String SD_YYYY_MM_DD_HH_MM_SS_LOG = "yyyy-MM-dd HH:mm:ss";

    private DateUtils() {
    }

    /**
     * 获取YYYY-MM-DD字符串
     *
     * @param time time
     * @return String
     */
    public static String getDataYearMonthDayFormat(long time) {
        return format(SD_YYYY_MM_DD_FORMATTER, new Date(time));
    }

    /**
     * 获取文件名上的时间
     */
    public static String getFileNameDate(long time) {
        return format(SD_YYYY_MM_DD_HH_MM_SS, new Date(time));
    }

    public static String getLogDate(long time) {
        return format(SD_YYYY_MM_DD_HH_MM_SS_LOG, new Date(time));
    }


    /**
     * 格式化时间使用
     *
     * @param type 格式化样式
     * @param date 格式化的时间
     * @return 格式化后的时间字符串
     */
    private static String format(String type, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(type, Locale.US);
        return format.format(date);
    }
}
