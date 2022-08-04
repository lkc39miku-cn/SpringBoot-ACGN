package com.hikari.project.login.service.impl;

import com.hikari.commons.key.CacheKey;
import com.hikari.commons.key.StringKey;
import com.hikari.commons.key.SysStaffKey;
import com.hikari.commons.util.IpUtils;
import com.hikari.commons.util.MessageUtils;
import com.hikari.commons.util.ServletUtils;
import com.hikari.framework.exception.captcha.CaptchaException;
import com.hikari.framework.exception.captcha.CaptchaExpireException;
import com.hikari.framework.exception.service.ServiceException;
import com.hikari.framework.exception.staff.StaffPasswordNotMatchException;
import com.hikari.framework.manager.AsyncManager;
import com.hikari.framework.manager.factory.AsyncFactory;
import com.hikari.framework.redis.RedisCache;
import com.hikari.project.login.entity.LoginBody;
import com.hikari.project.login.entity.LoginStaff;
import com.hikari.project.login.service.LoginService;
import com.hikari.project.system.entity.SysStaff;
import com.hikari.project.system.mapper.SysStaffMapper;
import com.hikari.project.system.service.impl.SysConfigServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * LoginServiceImpl
 *
 * @author lkc39miku_cn
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private RedisCache redisCache;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private SysConfigServiceImpl sysConfigServiceImpl;

    @Resource
    private SysStaffMapper sysStaffMapper;

    @Resource
    private TokenServiceImpl tokenServiceImpl;

    @Override
    public String login(LoginBody loginBody) {
        log.info("login 用户名称:{}", loginBody.getUsername());

        boolean captchaOnOff = Objects.isNull(sysConfigServiceImpl.selectCaptchaOnOff());
        if (!captchaOnOff) {
            validateCaptcha(loginBody);
        }

        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword()));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException ex) {
                AsyncManager.me().execute(AsyncFactory.recolonising(loginBody.getUsername(), SysStaffKey.LOGIN_FAIL, MessageUtils.message("staff.password.not.match")));
                throw new StaffPasswordNotMatchException();
            } else if (e instanceof ServiceException ex){
                AsyncManager.me().execute(AsyncFactory.recolonising(loginBody.getUsername(), SysStaffKey.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException("", ex.getMessage());
            }
            throw e;
        }

        AsyncManager.me().execute(AsyncFactory.recolonising(loginBody.getUsername(), SysStaffKey.LOGIN_SUCCESS, MessageUtils.message("staff.login.success")));
        LoginStaff loginStaff = (LoginStaff) authentication.getPrincipal();
        recordLoginInfo(loginStaff.getId());
        return tokenServiceImpl.createToken(loginStaff);
    }

    private void validateCaptcha(LoginBody loginBody) {
        String verifyKey = CacheKey.CAPTCHA_CODE_KEY + (StringUtils.isEmpty(loginBody.getUuid()) ? StringKey.NULL_STR : loginBody.getUuid());
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (StringUtils.isEmpty(captcha)) {
            AsyncManager.me().execute(AsyncFactory.recolonising(loginBody.getUsername(), SysStaffKey.LOGIN_FAIL, MessageUtils.message("staff.captcha.expire")));
            throw new CaptchaExpireException();
        }
        if (!loginBody.getCode().equalsIgnoreCase(captcha)) {
            AsyncManager.me().execute(AsyncFactory.recolonising(loginBody.getUsername(), SysStaffKey.LOGIN_FAIL, MessageUtils.message("staff.captcha.error")));
            throw new CaptchaException();
        }
    }

    private void recordLoginInfo(String id) {
        SysStaff staff = new SysStaff();
        staff.setId(id);
        staff.setEndLoginIp(IpUtils.getIpAddress(ServletUtils.httpServletRequest()));
        staff.setEndLoginTime(LocalDateTime.now());
        sysStaffMapper.updateByPrimaryKeySelective(staff);
    }
}
