package com.jia.jiacore.util;


import org.greenrobot.eventbus.EventBus;

/**
 * Description: EventBus 工具类
 * 1.用来Post
 * 2.注册接受对象
 * 3.反注册接受对象
 *
 * @author lrc19860926@gmail.com
 * @date 16/5/5 下午8:09
 */
public final class EventBusUtils {

    private EventBusUtils() {
    }

    /**
     * 注册
     *
     * @param receiverModel BaseEventReceiverModel
     */
    public static void register(Object receiverModel) {
        try {
            if (receiverModel != null) {
                EventBus.getDefault().register(receiverModel);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * 反注册
     *
     * @param receiverModel BaseEventReceiverModel
     */
    public static void unregister(Object receiverModel) {
        try {
            if (receiverModel != null) {
                EventBus.getDefault().unregister(receiverModel);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * Post
     *
     * @param eventModel Object
     */
    public static void post(Object eventModel) {
        try {
            EventBus.getDefault().post(eventModel);
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }
}
