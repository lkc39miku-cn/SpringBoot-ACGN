package com.hikari.framework.exception.user.valid;

import com.hikari.framework.exception.user.PixUserException;

import java.io.Serial;

/**
 * PixUserPasswordNotMatchException
 *
 * @author lkc39miku_cn
 */
public class PixUserPasswordNotMatchException extends PixUserException {
    @Serial
    private static final long serialVersionUID = 1012844028753522803L;

    public PixUserPasswordNotMatchException(String message) {
        super("pix_user_password_not_match", message);
    }
}
