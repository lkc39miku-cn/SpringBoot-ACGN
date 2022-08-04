package com.hikari.framework.exception.user.valid;

import com.hikari.framework.exception.user.PixUserException;

import java.io.Serial;

/**
 * PixUserExistException
 *
 * @author lkc39miku_cn
 */
public class PixUserExistException extends PixUserException {
    @Serial
    private static final long serialVersionUID = 6955008218104245412L;

    public PixUserExistException(String message) {
        super("pix_user_exist", message);
    }
}
