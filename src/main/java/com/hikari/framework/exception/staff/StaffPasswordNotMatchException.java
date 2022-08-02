package com.hikari.framework.exception.staff;

import java.io.Serial;

/**
 * StaffPasswordNotMatchException
 *
 * @author lkc39miku_cn
 */
public class StaffPasswordNotMatchException extends StaffException {
    @Serial
    private static final long serialVersionUID = 8717812559257487473L;

    public StaffPasswordNotMatchException() {
        super("Staff.A10001", "staff.password.not.match");
    }
}
