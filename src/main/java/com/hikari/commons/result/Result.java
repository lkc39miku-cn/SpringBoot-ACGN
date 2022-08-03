package com.hikari.commons.result;

import com.hikari.commons.enums.HttpServletResponse;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Result
 * @author lkc39miku_cn
 */
@Data
@Accessors(chain = true)
public class Result<T> {
    private Integer code;
    private String message;
    private T data;
    private Long count;

    public static <T> Result<T> success() {
        return new Result<T>()
                .setCode(HttpServletResponse.SC_OK.code())
                .setMessage(HttpServletResponse.SC_OK.message());
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>()
                .setCode(HttpServletResponse.SC_OK.code())
                .setMessage(HttpServletResponse.SC_OK.message())
                .setData(data);
    }

    public static <T> Result<T> success(T data, Long count) {
        return new Result<T>()
                .setCode(HttpServletResponse.SC_OK.code())
                .setMessage(HttpServletResponse.SC_OK.message())
                .setData(data)
                .setCount(count);
    }

    public static <T> Result<T> error() {
        return new Result<T>()
                .setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR.code())
                .setMessage(HttpServletResponse.SC_INTERNAL_SERVER_ERROR.message());
    }

    public static <T> Result<T> error(HttpServletResponse httpServletResponse) {
        return new Result<T>()
                .setCode(httpServletResponse.code())
                .setMessage(httpServletResponse.message());
    }

    public static <T> Result<T> error(T data) {
        return new Result<T>()
                .setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR.code())
                .setMessage(HttpServletResponse.SC_INTERNAL_SERVER_ERROR.message())
                .setData(data);
    }
}
