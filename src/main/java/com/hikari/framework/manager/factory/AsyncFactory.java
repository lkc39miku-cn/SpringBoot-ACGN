package com.hikari.framework.manager.factory;

import com.hikari.commons.key.SysStaffKey;
import com.hikari.commons.util.*;
import com.hikari.project.monitor.entity.SysLoginOs;
import com.hikari.project.monitor.service.SysLoginOsServiceImpl;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.TimerTask;

/**
 * AsyncFactory
 * @author lkc39miku_cn
 */
@Slf4j
public class AsyncFactory {

    public static TimerTask recolonising(String username, String status, String message, Object... args) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.httpServletRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddress(ServletUtils.httpServletRequest());
        return new TimerTask() {
            @Override
            public void run() {
                String address = AddressUtils.getRealAddressByIp(ip);
                String s = LoggerUtils.getBlock(ip) +
                        address +
                        LoggerUtils.getBlock(username) +
                        LoggerUtils.getBlock(status) +
                        LoggerUtils.getBlock(message);
                log.info(s, args);
                String os = userAgent.getOperatingSystem().getName();
                String browser = userAgent.getBrowser().getName();
                SysLoginOs loginOs = new SysLoginOs();
                loginOs.setUsername(username);
                loginOs.setIp(ip);
                loginOs.setLoginLocation(address);
                loginOs.setBrowser(browser);
                loginOs.setOs(os);
                loginOs.setMsg(message);
                loginOs.setLoginTime(LocalDateTime.now());
                if (StringUtils.equalsAny(status, SysStaffKey.LOGIN_SUCCESS, SysStaffKey.LOGOUT, SysStaffKey.REGISTER)) {
                    loginOs.setStatus(SysStaffKey.SUCCESS);
                } else if (SysStaffKey.LOGIN_FAIL.equals(status)) {
                    loginOs.setStatus(SysStaffKey.FAIL);
                }
                SpringUtils.getBean(SysLoginOsServiceImpl.class).insert(loginOs);
            }
        };
    }
}
