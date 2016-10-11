package com.jia.jiacore.network;

import com.jia.jiacore.R;
import com.jia.jiacore.exception.InitializationNotCompleteException;
import com.jia.jiacore.model.eventbus.event.ApiEndEvent;
import com.jia.jiacore.model.eventbus.event.ApiResultEvent;
import com.jia.jiacore.model.eventbus.event.ApiStartEvent;
import com.jia.jiacore.model.network.JsonModel;
import com.jia.jiacore.util.EventBusUtils;
import com.jia.jiacore.util.LogUtils;
import com.jia.jiacore.util.ResourceUtils;

import java.io.IOException;

import retrofit2.Response;

/**
 * Description: 请求执行者
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/28 上午11:16
 */

public final class ApiExecutor {

    /**
     * 当服务端返回code为1时 表示业务成功
     */
    private static final int BUSINESS_CODE_SUCCESS = 1;

    /**
     * HTTP SUCCESS CODE 200
     */
    private static final int HTTP_CODE_SUCCESS = 200;

    private IBaseRequest mRequest;

    private IBaseResponse mResponse;

    public ApiExecutor(IBaseRequest iBaseRequest) {
        mRequest = iBaseRequest;
        mResponse = new IBaseResponse();
    }

    /**
     * 执行请求
     */
    public void execute() {

        if (mRequest != null && mRequest.getCall() != null) {

            postStart();

            boolean isCheckOK = mRequest.check();

            if (isCheckOK) {
                try {
                    Response<JsonModel> response = mRequest.getCall().execute();

                    // http response code
                    int code = response.code();

                    // http response message
                    String message = response.message();

                    if (code == HTTP_CODE_SUCCESS && response.body() != null) {

                        JsonModel jsonModel = response.body();

                        // 正常返回
                        if (jsonModel.getStatusCode() == BUSINESS_CODE_SUCCESS) {

                            mResponse.setResultObj(jsonModel.getResult());

                        } else {
                            // 业务异常
                            mResponse.setFailedMessage(ResourceUtils.getString(R.string.network_failed_server));
                        }
                    } else {
                        // HTTP 非200异常
                        mResponse.setFailedMessage(ResourceUtils.getString(R.string.network_failed_server));
                        LogUtils.w("code:" + code + " message:" + message);
                    }

                } catch (IOException e) {
                    // 网络链接异常, 超时等
                    mResponse.setFailedMessage(ResourceUtils.getString(R.string.network_failed_connect));
                    LogUtils.wtf(e);
                } catch (Throwable e) {
                    // 例如字段不匹配等异常
                    mResponse.setFailedMessage(ResourceUtils.getString(R.string.network_failed_server));
                    LogUtils.wtf(e);
                }
            } else {
            }

            postEnd();

            postResult();

        } else {
            throw new InitializationNotCompleteException(
                    " mRequest and mRequest.getCall() must be not null. " +
                            " mRequest:" + mRequest +
                            " mRequest.getCall() :" + mRequest.getCall());
        }
    }

    /**
     * post 请求开始
     */
    private void postStart() {
        EventBusUtils.post(new ApiStartEvent(mRequest));
    }

    /**
     * post 请求结束
     */
    private void postEnd() {
        EventBusUtils.post(new ApiEndEvent(mRequest));
    }

    /**
     * post 请求结果
     */
    private void postResult() {
        EventBusUtils.post(new ApiResultEvent(mRequest, mResponse));
    }


}
