package com.hikari.commons.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.hikari.commons.key.CharsetKey;
import com.hikari.commons.key.IpKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

/**
 * AddressUtils
 * @author lkc39miku_cn
 */
@Slf4j
public class AddressUtils {

    @Value("${swagger.address-enabled}")
    private static Boolean addressEnabled;

    public static String getRealAddressByIp(final String ip) {
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        if (Boolean.TRUE.equals(addressEnabled)) {
            try {
                String rspStr = HttpUtils.sendGet(IpKey.GET_ADDRESS, "ip=" + ip + "&json=true", CharsetKey.GBK);
                if (StringUtils.isEmpty(rspStr)) {
                    log.error("获取地理位置异常 {}", ip);
                    return IpKey.ADDRESS_UNKNOWN;
                }

                JSONObject obj = JSON.parseObject(rspStr);
                String region = obj.getString("province");
                String city = obj.getString("city");
                return String.format("%s %s", region, city);
            }
            catch (Exception e) {
                log.error("获取地理位置异常 {}", ip);
            }
        }
        return IpKey.ADDRESS_UNKNOWN;
    }
}