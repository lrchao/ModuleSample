package com.lrchao.modulesample.network;

import com.jia.jiacore.network.ServiceFactory;

/**
 * Description: TODO
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/28 上午10:19
 */

public class ApiManager {

    public static final String API_HOST = "http://mob-user.zxpt.api.zx-erp.qeeka.com/mobile-user-service/";

    private static ApiManager sInstance;

    private ServerApi mServerApi;

    private ApiManager() {
        initServerApi();

    }

    private void initServerApi() {
        mServerApi = ServiceFactory.create(API_HOST, ServerApi.class);
    }

    public ServerApi getServerApi() {
        return mServerApi;
    }

    public static ApiManager getInstance() {
        synchronized (ApiManager.class) {
            if (sInstance == null) {
                sInstance = new ApiManager();
            }
        }
        return sInstance;
    }
}
