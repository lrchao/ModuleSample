package com.jia.jiacore.model.eventbus.receiver;

import android.support.annotation.MainThread;

import com.jia.jiacore.model.eventbus.event.ApiResultEvent;
import com.jia.jiacore.util.LogUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Description: EventBus的接受请求结果的Receiver
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/28 下午4:40
 */

public class ApiResultEventReceiver {

    @MainThread
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ApiResultEvent event) {
        LogUtils.e("event==" + event.getResponse());
    }

}
