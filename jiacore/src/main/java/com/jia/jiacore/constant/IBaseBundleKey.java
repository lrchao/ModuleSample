package com.jia.jiacore.constant;

/**
 * Description: 定义Intent 传递值的常量key or value
 *
 * @author lrc19860926@gmail.com
 * @date 16/9/27 下午3:42
 */

public abstract class IBaseBundleKey {

    /**
     * Dialog
     */
    public static final String INTENT_EXTRA_DIALOG_TITLE = "intent.extra.DIALOG_TITLE";
    public static final String INTENT_EXTRA_DIALOG_MESSAGE = "intent.extra.DIALOG_MESSAGE";
    public static final String INTENT_EXTRA_DIALOG_ICON = "intent.extra.DIALOG_ICON";
    public static final String INTENT_EXTRA_DIALOG_CANCELABLE = "intent.extra.DIALOG_CANCELABLE"; //Dialog的是否可以cancel
    public static final String INTENT_EXTRA_DIALOG_RIGHT_BTN_TEXT = "intent.extra.DIALOG_RIGHT_BTN_TEXT";
    public static final String INTENT_EXTRA_DIALOG_LEFT_BTN_TEXT = "intent.extra.DIALOG_LEFT_BTN_TEXT";
    public static final String INTENT_EXTRA_DIALOG_MIDDLE_BTN_TEXT = "intent.extra.DIALOG_MIDDLE_BTN_TEXT";
    public static final String INTENT_EXTRA_DIALOG_DELAY_DISMISS_TIME = "intent.extra.DIALOG_DELAY_DISMISS_TIME"; // dialog 自动消失的延迟时长
}
