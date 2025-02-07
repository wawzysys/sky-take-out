package com.sky.handler;

import com.sky.constant.MessageConstant;
import com.sky.exception.BaseException;
import com.sky.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice(basePackages = "com.sky.controller")
@Slf4j
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    // 正常返回值处理
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // 如果返回值类型已经是Result了，则不需要处理
        return !returnType.getParameterType().equals(Result.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, 
                                MethodParameter returnType,
                                MediaType selectedContentType,
                                Class selectedConverterType,
                                ServerHttpRequest request,
                                ServerHttpResponse response) {
        // 如果返回值为void或null，则返回成功响应
        if (body == null) {
            return Result.success();
        }
        // 其他情况，将返回值包装在Result中
        return Result.success(body);
    }

    // 异常处理部分
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error("异常信息：{}", ex.getMessage());
        String message = ex.getMessage();
        if (message.contains("Duplicate entry")){
            String[] split = message.split(" ");
            String username = split[2];
            String msg = username + MessageConstant.ALREAAY_EXIST;
            return Result.error(msg);
        }else{
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }
} 