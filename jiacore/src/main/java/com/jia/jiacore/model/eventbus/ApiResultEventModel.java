package com.jia.jiacore.model.eventbus;

import com.jia.jiacore.network.IBaseRequest;
import com.jia.jiacore.network.IBaseResponse;

/**
 * Description: EventBus 网络请求回调的model
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/28 上午11:32
 */

public final class ApiResultEventModel {

    private IBaseRequest mRequest;

    private IBaseResponse mResponse;

    public ApiResultEventModel(IBaseRequest request, IBaseResponse response) {
        mRequest = request;
        mResponse = response;
    }

    public IBaseRequest getRequest() {
        return mRequest;
    }

    public IBaseResponse getResponse() {
        return mResponse;
    }
}
