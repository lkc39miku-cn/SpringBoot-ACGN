package com.hikari.framework.exception.staff;

import com.hikari.framework.exception.SimpleException;

import java.io.Serial;

/**
 * StaffException
 *
 * @author lkc39miku_cn
 */
public class StaffException extends SimpleException {
    @Serial
    private static final long serialVersionUID = 2113562406789333870L;

    public StaffException(String code, String message) {
        super(code, message, "staff");
    }
}
