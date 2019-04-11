package com.zf.common.exception;

import co.legu.modules.common.bean.Result;
import lombok.Getter;

/**
 * 用于必须抛出异常，又想给前端传递信息的情况<br>
 */
public class MessageException extends RuntimeException {
    /**
     * 封装的返回值，如果存在则异常处理器会以这个为准做返回
     */
    @Getter
    private final Result<?> result;

    public MessageException() {
        this.result = null;
    }

    public MessageException(Result<?> result) {
        this.result = result;
    }

    public MessageException(String message) {
        super(message);
        this.result = null;
    }

    public MessageException(String message, Throwable cause) {
        super(message, cause);
        this.result = null;
    }

    public MessageException(Throwable cause) {
        super(cause);
        this.result = null;
    }

    public MessageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.result = null;
    }
}
