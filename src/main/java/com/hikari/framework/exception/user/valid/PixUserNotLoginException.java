package com.hikari.framework.exception.user.valid;

import com.hikari.framework.exception.user.PixUserException;

import java.io.Serial;

/**
 * PixUserNotLoginException
 *
 * @author lkc39miku_cn
 */
public class PixUserNotLoginException extends PixUserException {

    @Serial
    private static final long serialVersionUID = -637301642562580293L;

    public PixUserNotLoginException(String message) {
        super("pix_user_not_login", message);
    }
}
