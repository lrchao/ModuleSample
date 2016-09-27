package com.jia.jiacore.manager.shared_preference;

/**
 * Description: 公共的sharedPreference
 * logout不会清除的，和 设备相关的
 *
 * @author lrc19860926@gmail.com
 * @date 15/11/12 下午3:58
 */
public class CommonSharedPreference extends IBaseSharedPreference {

    /**
     * 配置文件名字
     */
    private static final String NAME = "common_preferences";

    private static CommonSharedPreference sInstance;

    /**
     * @return CommonSharedPreference
     */
    public static CommonSharedPreference getsInstance() {
        synchronized (CommonSharedPreference.class) {
            if (sInstance == null) {
                sInstance = new CommonSharedPreference();
            }
        }
        return sInstance;
    }

    @Override
    protected String getName() {
        return NAME;
    }


}
