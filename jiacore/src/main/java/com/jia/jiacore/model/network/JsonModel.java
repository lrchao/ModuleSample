package com.jia.jiacore.model.network;

import com.google.gson.annotations.SerializedName;

/**
 * Description: TODO
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/28 上午10:01
 */

public class JsonModel<T> {

    @SerializedName("status")
    private int mStatusCode;

    @SerializedName("message")
    private String mResponseMsg;

    @SerializedName("result")
    private T mResult;

    public int getStatusCode() {
        return mStatusCode;
    }

    public String getResponseMsg() {
        return mResponseMsg;
    }

    public T getResult() {
        return mResult;
    }
}
