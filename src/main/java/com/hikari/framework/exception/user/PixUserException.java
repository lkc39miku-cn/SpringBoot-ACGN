package com.hikari.framework.exception.user;

import com.hikari.framework.exception.SimpleException;

import java.io.Serial;

/**
 * PixUserException
 *
 * @author lkc39miku_cn
 */
public class PixUserException extends SimpleException {
    @Serial
    private static final long serialVersionUID = 8800475361856315190L;

    public PixUserException(String code, String message) {
        super(code, message, "pix_user");
    }
}
