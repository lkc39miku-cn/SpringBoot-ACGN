package com.hikari.commons.util;

import java.util.Objects;

/**
 * LoggerUtils
 *
 * @author lkc39miku_cn
 */
public class LoggerUtils {
    public static String getBlock(Object msg) {
        if (Objects.isNull(msg)) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
