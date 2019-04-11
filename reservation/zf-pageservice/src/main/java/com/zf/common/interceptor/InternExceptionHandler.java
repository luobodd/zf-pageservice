package com.zf.common.interceptor;

import co.legu.modules.common.bean.Result;
import co.legu.modules.common.bean.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zf.common.exception.MessageException;
import com.zf.config.MvcConfig;

/**
 * 公共异常处理器，用于处理控制器抛出的异常
 */
@ControllerAdvice(MvcConfig.BASE_PACKAGE)
@Slf4j
public class InternExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Result<?>> handleException(Exception e) {
        // 输出错误日志
        log.error("", e);

        // 默认输出服务器内部错误
        Result<?> r = Result.fail("服务器内部错误", ResultCode.INTERNAL_SERVER_ERROR.getCode());

        // 调试模式下可以输出具体的错误信息
        if (MvcConfig.isDebug()) {
            r.setMessage(e.getMessage());
        } else if (e instanceof MethodArgumentNotValidException || e instanceof ServletRequestBindingException) {
            // 参数检查错误
            r
                    .setMessage("参数检查错误")
                    .setCode(ResultCode.FAIL.getCode());
        }

        // MessageException 可输出具体的信息或直接输出内部附带的 Result
        if (e instanceof MessageException) {
            // 如果不附带 Result，则使用异常附带的信息，否则使用附带的 Result
            MessageException me = (MessageException) e;
            if (me.getResult() != null) {
                r = me.getResult();
            } else {
                r.setMessage(me.getMessage());
            }
        }

        if (r.getMessage() == null) {
            r.setMessage("");
        }

        // 返回结果
        return new ResponseEntity<>(r, HttpStatus.OK);
    }
}
