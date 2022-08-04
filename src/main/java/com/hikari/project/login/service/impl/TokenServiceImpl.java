package com.hikari.project.login.service.impl;

import com.hikari.commons.key.CacheKey;
import com.hikari.commons.key.TokenKey;
import com.hikari.commons.util.*;
import com.hikari.framework.redis.RedisCache;
import com.hikari.project.login.entity.LoginStaff;
import com.hikari.project.login.service.TokenService;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * TokenServiceImpl
 *
 * @author lkc39miku_cn
 */
@Slf4j
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${jwt.header}")
    private String header;
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expireTime}")
    private Integer expireTime;

    private static final long MILLIS_SECONDS = 1;
    private static final long MILLIS_MINUTE = 60;
    private static final long MILLIS_MINUTE_TEN = 600;

    @Resource
    private RedisCache redisCache;

    @Override
    public String createToken(LoginStaff loginStaff) {
        String token = IdUtils.uuid();
        loginStaff.setToken(token);
        setUserAgent(loginStaff);
        refreshToken(loginStaff);

        Map<String, Object> claims = new HashMap<>(1);
        claims.put(CacheKey.LOGIN_STAFF_KEY, token);
        return createToken(claims);
    }

    private void setUserAgent(LoginStaff loginStaff) {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.httpServletRequest().getHeader("User-Agent"));
        String ip = IpUtils.getIpAddress(ServletUtils.httpServletRequest());
        loginStaff.setIp(ip);
        loginStaff.setLoginLocation(AddressUtils.getRealAddressByIp(ip));
        loginStaff.setBrowser(userAgent.getBrowser().getName());
        loginStaff.setOs(userAgent.getOperatingSystem().getName());
    }

    private void refreshToken(LoginStaff loginStaff) {
        loginStaff.setLoginTime(LocalDateTime.now());
        loginStaff.setExpireTime(loginStaff.getLoginTime().plusSeconds(expireTime * MILLIS_MINUTE));
        String userKey = getTokenKey(loginStaff.getToken());
        redisCache.setCacheObject(userKey, loginStaff, expireTime, TimeUnit.MINUTES);
    }

    private String getTokenKey(String uuid) {
        return CacheKey.LOGIN_TOKEN_KEY + uuid;
    }

    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public void deleteLoginStaff(String token) {
        if (StringUtils.isNoneEmpty(token)) {
            String userKey = getTokenKey(token);
            redisCache.deleteObject(userKey);
        }
    }

    @Override
    public void setLoginStaff(LoginStaff loginStaff) {
        if (Objects.nonNull(loginStaff) && StringUtils.isNoneEmpty(loginStaff.getToken())) {
            refreshToken(loginStaff);
        }
    }

    @Override
    public void verifyToken(LoginStaff loginStaff) {
        LocalDateTime ex = loginStaff.getExpireTime();
        LocalDateTime currentTime = LocalDateTime.now();
        if (DateUtils.getDurationSeconds(ex, currentTime) <= MILLIS_MINUTE_TEN) {
            refreshToken(loginStaff);
        }
    }

    @Override
    public LoginStaff getLoginStaff(HttpServletRequest request) {
        String token = getToken(request);
        if (StringUtils.isNoneEmpty(token)) {
            try {
                Claims claims = parseToken(token);
                String uuid = (String) claims.get(CacheKey.LOGIN_STAFF_KEY);
                String key = getTokenKey(uuid);
                return redisCache.getCacheObject(key);
            } catch (Exception e) {
                log.error("get login staff error: {}", e.getMessage());
            }
        }
        return null;
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNoneEmpty(token) && token.startsWith(TokenKey.TOKEN_PREFIX)) {
            token = token.replace(TokenKey.TOKEN_PREFIX, "");
        }
        return token;
    }
}
