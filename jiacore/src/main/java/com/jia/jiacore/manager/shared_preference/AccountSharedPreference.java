package com.jia.jiacore.manager.shared_preference;

/**
 * Description: 存放当前用户相关的配置文件
 *
 * @author lrc19860926@gmail.com
 * @date 16/3/19 下午4:38
 */
public class AccountSharedPreference extends IBaseSharedPreference {

    /**
     * 配置文件名字
     */
    private static final String NAME = "account_preferences";

    private static AccountSharedPreference sInstance;

    /**
     * @return AccountSharedPreference
     */
    public static AccountSharedPreference getsInstance() {
        synchronized (AccountSharedPreference.class) {
            if (sInstance == null) {
                sInstance = new AccountSharedPreference();
            }
        }
        return sInstance;
    }

    @Override
    protected String getName() {
        return NAME;
    }

    public void setSharedPreferenceKey() {

    }

}
