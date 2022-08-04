package com.hikari.project.login.controller;

import com.google.code.kaptcha.Producer;
import com.hikari.commons.key.CacheKey;
import com.hikari.commons.result.Result;
import com.hikari.commons.util.IdUtils;
import com.hikari.framework.redis.RedisCache;
import com.hikari.project.system.service.impl.SysConfigServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * CaptchaController
 *
 * @author lkc39miku_cn
 */
@Slf4j
@RestController
public class CaptchaController {
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Resource
    private RedisCache redisCache;

    @Value("${producer.captcha-type}")
    private String captchaType;

    @Value("${producer.captcha-expiration}")
    private Integer captchaExpiration;

    @Resource
    private SysConfigServiceImpl sysConfigServiceImpl;

    @GetMapping("/captcha/image")
    public Result<Map<String, Object>> getCode(HttpServletResponse response) throws IOException {
        Map<String, Object> map = new HashMap<>(3);
//        boolean captchaOnOff = configService.selectCaptchaOnOff();
        boolean captchaOnOff = true;
        map.put("captchaOnOff", captchaOnOff);
        if (!captchaOnOff) {
            return Result.success(map);
        }

        String uuid = IdUtils.uuid();
        String verifyKey = CacheKey.CAPTCHA_CODE_KEY + uuid;

        String capStr, code = null;
        BufferedImage image = null;

        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        redisCache.setCacheObject(verifyKey, code, captchaExpiration, TimeUnit.MINUTES);
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            assert image != null;
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return Result.error();
        }

        map.put("uuid", uuid);
        map.put("img", Base64.encodeBase64String(os.toByteArray()));
        return Result.success(map);
    }
}
