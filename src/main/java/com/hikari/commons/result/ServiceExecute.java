package com.hikari.commons.result;

import com.hikari.commons.key.NumberKey;
import com.hikari.framework.exception.service.ServiceException;

/**
 * ServiceExecute
 *
 * @author lkc39miku_cn
 */
public class ServiceExecute {
    public enum ExecuteStatus {
        INSERT("添加出现错误"),
        UPDATE("修改出现错误"),
        DELETE("删除出现错误");

        private final String message;
        ExecuteStatus(String message) {
            this.message = message;
        }
    }

    public static void compare(int count, ExecuteStatus executeStatus) {
        if (count <= NumberKey.ZERO) throw new ServiceException("Service.A10001", executeStatus.message);
    }

    public static void compare(int count, int size, ExecuteStatus executeStatus) {
        if (count <= NumberKey.ZERO || size <= NumberKey.ZERO || count != size) throw new ServiceException("Service.A10001", executeStatus.message);
    }

    public static void compare(boolean flag, ExecuteStatus executeStatus) {
        if (!flag) throw new ServiceException("Service.A10001", executeStatus.message);
    }
}
