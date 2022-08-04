package com.hikari.framework.exception.user.valid;

import com.hikari.framework.exception.user.PixUserException;

import java.io.Serial;

/**
 * PixUserBlackRedisException
 *
 * @author lkc39miku_cn
 */
public class PixUserBlackRedisException extends PixUserException {

    @Serial
    private static final long serialVersionUID = -4360863233354605301L;

    public PixUserBlackRedisException(String message) {
        super("pix_user_black_redis", message);
    }
}
