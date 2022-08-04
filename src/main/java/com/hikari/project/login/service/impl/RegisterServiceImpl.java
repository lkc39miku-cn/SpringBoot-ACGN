package com.hikari.project.login.service.impl;

import com.hikari.commons.key.CacheKey;
import com.hikari.commons.key.StringKey;
import com.hikari.commons.key.SysStaffKey;
import com.hikari.commons.util.MessageUtils;
import com.hikari.commons.util.SecurityUtils;
import com.hikari.framework.exception.captcha.CaptchaException;
import com.hikari.framework.exception.captcha.CaptchaExpireException;
import com.hikari.framework.manager.AsyncManager;
import com.hikari.framework.manager.factory.AsyncFactory;
import com.hikari.framework.redis.RedisCache;
import com.hikari.project.login.entity.RegisterBody;
import com.hikari.project.login.service.RegisterService;
import com.hikari.project.system.entity.SysStaff;
import com.hikari.project.system.service.impl.SysConfigServiceImpl;
import com.hikari.project.system.service.impl.SysStaffServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * RegisterServiceImpl
 *
 * @author lkc39miku_cn
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    @Resource
    private RedisCache redisCache;

    @Resource
    private SysStaffServiceImpl sysStaffServiceImpl;

    @Resource
    private SysConfigServiceImpl sysConfigServiceImpl;

    @Override
    public String register(RegisterBody registerBody) {
        String msg = "";
        String username = registerBody.getUsername();

        boolean captchaOnOff = Objects.isNull(sysConfigServiceImpl.selectCaptchaOnOff());
        if (!captchaOnOff) {
            validateCaptcha(registerBody);
        }

        if (sysStaffServiceImpl.checkStaffUsername(username)) {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        } else {
            SysStaff staff = new SysStaff();
            staff.setUsername(username);
            staff.setNickname(username);
            staff.setPassword(SecurityUtils.encryptPassword(registerBody.getPassword()));
            boolean regFlag = sysStaffServiceImpl.registerStaff(staff);
            if (regFlag) {
                msg = "注册失败,请联系系统管理人员";
            } else {
                AsyncManager.me().execute(AsyncFactory.recolonising(username, SysStaffKey.REGISTER,
                        MessageUtils.message("staff.register.success")));
            }
        }
        return msg;
    }

    private void validateCaptcha(RegisterBody registerBody) {
        String verifyKey = CacheKey.CAPTCHA_CODE_KEY + (StringUtils.isEmpty(registerBody.getUuid()) ? StringKey.NULL_STR : registerBody.getUuid());
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (StringUtils.isEmpty(captcha)) {
            throw new CaptchaExpireException();
        }
        if (!registerBody.getCode().equalsIgnoreCase(captcha)) {
            throw new CaptchaException();
        }
    }
}
