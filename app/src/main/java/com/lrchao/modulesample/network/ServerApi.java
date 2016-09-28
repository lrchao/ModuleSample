package com.lrchao.modulesample.network;

import com.jia.jiacore.model.network.JsonModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Description: 网络请求接口定义
 * http://mob-user.zxpt.api.zx-erp.qeeka.com/mobile-user-service/
 * @author lrc19860926@gmail.com
 * @date 16/9/28 上午9:58
 */

public interface ServerApi {

    /**
     * 首页
     */
    @GET("homepage1111/info")
    Call<JsonModel> homepage();

    /**
     * 申请设计师
     */
    @FormUrlEncoded
    @POST("apply/designer")
    Call<JsonModel> applyDesigner(
            @Field("content") String content);
}
