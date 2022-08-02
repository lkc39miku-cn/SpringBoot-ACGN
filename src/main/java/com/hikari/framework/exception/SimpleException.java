package com.hikari.framework.exception;

import java.io.Serial;

/**
 * SimpleException
 *
 * @author lkc39miku_cn
 */
public class SimpleException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -5708051897123269581L;
    private final String code;
    private final String message;
    private final String module;

    public SimpleException(String code, String message, String module) {
        this.code = code;
        this.message = message;
        this.module = module;
    }
}
