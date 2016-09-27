package com.jia.jiacore.exception;

/**
 * Description: 参数非法异常
 *
 * @author lrc19860926@gmail.com
 * @date 15/12/21 下午2:52
 */
public class IllegalParamException extends RuntimeException {

    public IllegalParamException(String detailMessage) {
        super(detailMessage);
    }


}
