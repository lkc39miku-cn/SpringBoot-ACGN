package com.hikari.commons.util;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    public static Long getDateSubSeconds(Date startTime, Date endTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startLocalDateTime = LocalDateTime.parse(simpleDateFormat.format(startTime), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endLocalDateTime = LocalDateTime.parse(simpleDateFormat.format(endTime), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return Duration.between(startLocalDateTime, endLocalDateTime).toMillis() / 1000;
    }
}