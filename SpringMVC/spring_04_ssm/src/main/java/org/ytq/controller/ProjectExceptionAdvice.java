package org.ytq.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.ytq.exception.BusinessException;
import org.ytq.exception.SystemException;

// 异常处理器

@RestControllerAdvice
// REST风格对应的异常处理器
public class ProjectExceptionAdvice {
    @ExceptionHandler(SystemException .class)

    public Result doSystemException(SystemException ex){
        // 记录日志

        // 发送消息给用户

        // 发送邮件给开发者

        return new Result(ex.getCode(), null, ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException ex){
        return new Result(ex.getCode(), null, ex.getMessage());
    }
    @ExceptionHandler(Exception.class)

    public Result doException(Exception ex){
        System.out.println("exception is detected!");
        return new Result(Code.SYSTEM_UNKNOWN_ERR, null, "系统繁忙，请稍后尝试");
    }
}
