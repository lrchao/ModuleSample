package com.jia.jiacore.manager.shared_preference;

import java.util.HashSet;

/**
 * Description: SharedPreferenceItem
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/27 上午11:28
 */

public final class SharedPreferenceItem {

    /**
     * 字符串的key
     */
    private String mKey;

    /**
     * 默认值
     */
    private Object mDefaultValue;

    /**
     * 对应的类型
     */
    private Class mClazz;

    public SharedPreferenceItem(String key, Class clazz) {
        mKey = key;
        mClazz = clazz;
    }

    public SharedPreferenceItem(String key, Class clazz, Object defaultValue) {
        mKey = key;
        mClazz = clazz;
        mDefaultValue = defaultValue;
    }

    public String getKey() {
        return mKey;
    }

    public Class getClazz() {
        return mClazz;
    }


    /**
     * 获取默认值
     */
    public Object getDefaultValue() {

        if (mDefaultValue != null) {
            return mDefaultValue;
        }

        if (getClazz() == Boolean.class) {
            mDefaultValue = PreferenceOperator.DEFAULT_BOOLEAN;

        } else if (getClazz() == Integer.class) {
            mDefaultValue = PreferenceOperator.DEFAULT_INTEGER;

        } else if (getClazz() == String.class) {
            mDefaultValue = PreferenceOperator.DEFAULT_STRING;

        } else if (getClazz() == Long.class) {
            mDefaultValue = PreferenceOperator.DEFAULT_LONG;

        } else if (getClazz() == Float.class) {
            mDefaultValue = PreferenceOperator.DEFAULT_FLOAT;

        } else if (getClazz() == HashSet.class) {
            mDefaultValue = PreferenceOperator.DEFAULT_SET;
        }

        return mDefaultValue;
    }
}
