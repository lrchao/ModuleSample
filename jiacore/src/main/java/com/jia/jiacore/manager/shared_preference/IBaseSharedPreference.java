package com.jia.jiacore.manager.shared_preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jia.jiacore.IBaseApplication;
import com.jia.jiacore.exception.IllegalParamException;

import java.util.HashSet;

/**
 * Description: SharedPreferenceManager的基类
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/27 上午11:31
 */

public abstract class IBaseSharedPreference {

    /**
     * 具体SharedPreference对象
     */
    protected SharedPreferences mSharedPreferences;

    /**
     * 操作类
     */
    private PreferenceOperator mOperator;

    public IBaseSharedPreference() {
        mSharedPreferences = IBaseApplication.getApplication().getSharedPreferences(getName(), Context.MODE_PRIVATE);
        mOperator = new PreferenceOperator(mSharedPreferences);
    }

    // =====================
    // public
    // =====================

    /**
     * 注册监听变化,必须在OnDestroy中反注册
     *
     * @param listener SharedPreferences.OnSharedPreferenceChangeListener
     */
    public void registerOnChangedListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mSharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }

    /**
     * 须在OnDestroy中反注册
     *
     * @param listener SharedPreferences.OnSharedPreferenceChangeListener
     */
    public void unregisterOnChangedListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    }

    /**
     * 清空缓存
     */
    public void clear() {
        mOperator.clear();
    }

    /**
     * 存储缓存
     *
     * @param sharedPreferenceKey SharedPreferenceKey
     * @param value               value
     */
    public void setValue(SharedPreferenceItem sharedPreferenceKey, @NonNull Object value) {

        if (value == null) {
            return;
        }

        // 判断类型是否合法
        if (!value.getClass().equals(sharedPreferenceKey.getClazz())) {
            throw new IllegalParamException("param type is illegal key class:" +
                    sharedPreferenceKey.getClazz() + " value:" + value.getClass());
        }
        mOperator.setValue(sharedPreferenceKey.getKey(), value);
    }

    /**
     * 获取Set string
     *
     * @param sharedPreferenceKey key
     * @return Set
     */
    @Nullable
    public HashSet<String> getStringSet(SharedPreferenceItem sharedPreferenceKey) {
        Object obj = getValue(sharedPreferenceKey);
        if (obj == null) {
            return PreferenceOperator.DEFAULT_SET;
        }

        if (obj instanceof HashSet) {
            return (HashSet) obj;
        } else {
            return PreferenceOperator.DEFAULT_SET;
        }
    }

    /**
     * 获取boolean的值
     *
     * @param sharedPreferenceKey pref key
     */
    public boolean getBooleanValue(SharedPreferenceItem sharedPreferenceKey) {
        Object obj = getValue(sharedPreferenceKey);
        if (obj == null) {
            return PreferenceOperator.DEFAULT_BOOLEAN;
        }

        if (obj instanceof Boolean) {
            return (boolean) obj;
        } else {
            return PreferenceOperator.DEFAULT_BOOLEAN;
        }
    }

    /**
     * 获取string的值
     *
     * @param sharedPreferenceKey pref key
     */
    public String getStringValue(SharedPreferenceItem sharedPreferenceKey) {
        Object obj = getValue(sharedPreferenceKey);
        if (obj == null) {
            return PreferenceOperator.DEFAULT_STRING;
        }

        if (obj instanceof String) {
            return (String) obj;
        } else {
            return PreferenceOperator.DEFAULT_STRING;
        }
    }

    /**
     * 获取int的值
     *
     * @param sharedPreferenceKey pref key
     */
    public int getIntValue(SharedPreferenceItem sharedPreferenceKey) {
        Object obj = getValue(sharedPreferenceKey);
        if (obj == null) {
            return PreferenceOperator.DEFAULT_INTEGER;
        }

        if (obj instanceof Integer) {
            return (int) obj;
        } else {
            return PreferenceOperator.DEFAULT_INTEGER;
        }
    }

    /**
     * 获取long的值
     *
     * @param sharedPreferenceKey pref key
     */
    public long getLongValue(SharedPreferenceItem sharedPreferenceKey) {
        Object obj = getValue(sharedPreferenceKey);
        if (obj == null) {
            return PreferenceOperator.DEFAULT_LONG;
        }

        if (obj instanceof Long) {
            return (long) obj;
        } else {
            return PreferenceOperator.DEFAULT_LONG;
        }
    }

    /**
     * 获取float的值
     *
     * @param sharedPreferenceKey pref key
     */
    public float getFloatValue(SharedPreferenceItem sharedPreferenceKey) {
        Object obj = getValue(sharedPreferenceKey);
        if (obj == null) {
            return PreferenceOperator.DEFAULT_FLOAT;
        }

        if (obj instanceof Float) {
            return (float) obj;
        } else {
            return PreferenceOperator.DEFAULT_FLOAT;
        }
    }

    // =====================
    // private
    // =====================


    /**
     * @param sharedPreferenceKey SharedPreferenceKey
     * @return value
     */
    private Object getValue(SharedPreferenceItem sharedPreferenceKey) {

        Object valueObj;
        try {
            valueObj = mOperator.getValue(sharedPreferenceKey.getClazz(),
                    sharedPreferenceKey.getKey(),
                    sharedPreferenceKey.getDefaultValue());
        } catch (ClassCastException e) {
            // 类型错误时，取默认值
            valueObj = sharedPreferenceKey.getDefaultValue();
        }

        return valueObj;
    }


    /**
     * 子类实现 设置sharedPreference name
     */
    protected abstract String getName();
}
