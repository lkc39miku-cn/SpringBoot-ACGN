package com.hikari.commons.util;

import java.util.UUID;

/**
 * IdUtils
 *
 * @author lkc39miku_cn
 */
public class IdUtils {
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
