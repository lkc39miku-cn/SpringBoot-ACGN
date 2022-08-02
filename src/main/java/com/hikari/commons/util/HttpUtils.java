package com.hikari.commons.util;

import com.hikari.commons.key.CharsetKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

/**
 * HttpUtils
 * @author lkc39miku_cn
 */
@Slf4j
public class HttpUtils {
    public static String sendGet(String url, String param) {
        return sendGet(url, param, CharsetKey.UTF_8);
    }

    public static String sendGet(String url, String param, String contentType) {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try {
            String urlNameString = StringUtils.isNotEmpty(param) ? url + "?" + param : url;
            log.info("sendGet - {}", urlNameString);
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), contentType));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            log.info("rev - {}", result);
        } catch (IOException e) {
            log.error("调用HttpUtils.sendGet IOException, url: {}, param: {}", url, param, e);
        } finally {
            try {
                if (Objects.nonNull(in)) {
                    in.close();
                }
            } catch (Exception ex) {
                log.error("调用in.close Exception, url: {}, param: {}", url, param, ex);
            }
        }
        return result.toString();
    }
}