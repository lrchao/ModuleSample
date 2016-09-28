package com.lrchao.modulesample.network;

import com.jia.jiacore.network.IBaseRequest;

import retrofit2.Call;

/**
 * Description: 首页的请求
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/28 上午10:41
 */

public class HomepageRequest extends IBaseRequest {

    @Override
    public Call getCall() {
        return ApiManager.getInstance().getServerApi().homepage();
    }
}
