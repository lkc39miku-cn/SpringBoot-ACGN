package com.hikari.framework.exception.captcha;

import com.hikari.framework.exception.staff.StaffException;

import java.io.Serial;

/**
 * CaptchaException
 * @author lkc39miku_cn
 */
public class CaptchaException extends StaffException {

    @Serial
    private static final long serialVersionUID = 3421361064457849403L;

    public CaptchaException()
    {
        super("staff.captcha.error", null);
    }
}
