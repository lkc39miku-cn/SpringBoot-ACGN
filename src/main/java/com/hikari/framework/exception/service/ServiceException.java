package com.hikari.framework.exception.service;

import com.hikari.framework.exception.SimpleException;

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
}
