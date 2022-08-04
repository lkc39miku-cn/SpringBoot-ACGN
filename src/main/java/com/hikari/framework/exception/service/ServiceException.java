package com.hikari.framework.exception.service;

import com.hikari.commons.enums.HttpServletResponse;
import com.hikari.framework.exception.SimpleException;
import lombok.Getter;

import java.io.Serial;

/**
 * ServiceException
 *
 * @author lkc39miku_cn
 */
public class ServiceException extends SimpleException {
    @Serial
    private static final long serialVersionUID = 1866836799217219697L;

    public ServiceException(String code, String message) {
        super(code, message, "service");
    }

    public ServiceException(HttpServletResponse httpServletResponse) {
        super(httpServletResponse.code().toString(), httpServletResponse.message(), "service");
    }
}
