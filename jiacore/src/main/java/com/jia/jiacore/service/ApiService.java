package com.jia.jiacore.service;

import android.app.IntentService;
import android.content.Intent;

import com.jia.jiacore.IBaseApplication;
import com.jia.jiacore.network.ApiExecutor;
import com.jia.jiacore.network.IBaseRequest;

/**
 * Description: 处理网络请求的Service
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/28 上午9:49
 */

public final class ApiService extends IntentService {

    private static final String INTENT_EXTRA_REQUEST = "intent.extra.REQUEST";

    public ApiService() {
        super("ApiService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        IBaseRequest iBaseRequest = (IBaseRequest) intent.getSerializableExtra(INTENT_EXTRA_REQUEST);
        ApiExecutor executor = new ApiExecutor(iBaseRequest);
        executor.execute();

    }

    /**
     * @param request IBaseRequest
     * @return 获取启动deIntent
     */
    public static Intent createIntent(IBaseRequest request) {
        Intent intent = new Intent(IBaseApplication.getApplication(), ApiService.class);
        intent.putExtra(INTENT_EXTRA_REQUEST, request);
        return intent;
    }
}
