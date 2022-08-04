package com.hikari.framework.exception;

import com.alibaba.fastjson2.JSON;
import com.hikari.commons.enums.HttpServletResponse;
import com.hikari.commons.result.Result;
import com.hikari.commons.util.ServletUtils;
import com.hikari.framework.exception.service.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * GlobalExceptionHandler
 * return all exception advice
 * @author lkc39miku_cn
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {HttpRequestMethodNotSupportedException.class})
    public <T> Result<T> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        String uri = request.getRequestURI();
        log.error("请求地址'{}',不支持'{}'请求", uri, e.getMethod());
        return Result.error(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = {ServiceException.class})
    public Result<String> handleServiceException (ServiceException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        String code = e.getCode();
        return Objects.isNull(code) ? Result.error(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage()) : Result.error(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, code);
    }

    @ExceptionHandler(value = {SimpleException.class})
    public void handleBaseException(SimpleException e, HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>(4);

        if (StringUtils.isNotEmpty(e.getModule())) {
            map.put("module", e.getModule());
        }

        if (Objects.nonNull(e.getCode())) {
            map.put("code", e.getCode());
        }

        if (StringUtils.isNotEmpty(e.getMessage())) {
            map.put("message", e.getMessage());
        }

        ServletUtils.render(response, JSON.toJSONString(map));
    }

    @ExceptionHandler(value = {Exception.class})
    public <T> Result<T> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
    }
}
