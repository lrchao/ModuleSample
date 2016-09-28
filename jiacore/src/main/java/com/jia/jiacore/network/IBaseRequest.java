package com.jia.jiacore.network;

import com.jia.jiacore.model.network.startview.IBaseRequestStartViewModel;

import java.io.Serializable;

import retrofit2.Call;

/**
 * Description: Request的基类
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/28 上午10:41
 */

public abstract class IBaseRequest implements Serializable {

    /**
     * 设置retrofit2 Call
     */
    public abstract Call getCall();

    /**
     * 检查请求
     * @return true:执行请求 false: 不执行请求
     */
    protected boolean check(){
        return true;
    }

    public IBaseRequestStartViewModel getStartViewModel() {
        return null;
    }




}
