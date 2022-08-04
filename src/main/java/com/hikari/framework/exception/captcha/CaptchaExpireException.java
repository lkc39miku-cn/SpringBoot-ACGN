package com.hikari.framework.exception.captcha;

import com.hikari.framework.exception.staff.StaffException;

import java.io.Serial;

/**
 * CaptchaExpireException
 * @author lkc39miku_cn
 */
public class CaptchaExpireException extends StaffException {

    @Serial
    private static final long serialVersionUID = -7701875112786045863L;

    public CaptchaExpireException() {
        super("staff.captcha.expire", null);
    }
}
