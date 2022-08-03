package com.hikari.commons.util;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * DateUtils
 * @author lkc39miku_cn
 */
public class DateUtils {
    public static Long getDurationMinutes(LocalDateTime v1, LocalDateTime v2) {
        Duration duration = Duration.between(v1, v2);
        return duration.toMinutes();
    }

    public static Long getDurationSeconds(LocalDateTime v1, LocalDateTime v2) {
        Duration duration = Duration.between(v1, v2);
        return duration.toSeconds();
    }

    public static Long getDurationHour(LocalDateTime v1, LocalDateTime v2) {
        Duration duration = Duration.between(v1, v2);
        return duration.toHours();
    }

    public static Long getDurationDays(LocalDateTime v1, LocalDateTime v2) {
        Duration duration = Duration.between(v1, v2);
        return duration.toDays();
    }
}