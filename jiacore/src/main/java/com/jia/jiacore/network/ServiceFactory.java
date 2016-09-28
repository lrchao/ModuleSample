package com.jia.jiacore.network;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Description: 生成ApiService
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/28 上午10:17
 */

public final class ServiceFactory {

    private ServiceFactory() {
    }

    /**
     * 生成instance  of ServiceApi
     *
     * @param apiHost Host
     * @param clazz   Service class
     * @param <T>     service instance
     * @return
     */
    public static <T> T create(String apiHost, Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiHost)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }
}
