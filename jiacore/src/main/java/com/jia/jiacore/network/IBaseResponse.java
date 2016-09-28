package com.jia.jiacore.network;

/**
 * Description: TODO
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/28 上午11:15
 */

public class IBaseResponse {

    private Object mResultObj;

    private String mFailedMessage;

    public void setFailedMessage(String failedMessage) {
        mFailedMessage = failedMessage;
    }

    public void setResultObj(Object resultObj) {
        mResultObj = resultObj;
    }
}
