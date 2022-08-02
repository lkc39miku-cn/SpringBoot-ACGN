package com.hikari.framework.config;

import com.hikari.commons.util.ServletUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

/**
 * Serve
 * @author lkc39miku_cn
 */
@Configuration
public class Server {
    public String getUrl() {
        HttpServletRequest request = ServletUtils.httpServletRequest();
        return getRequestUrl(request);
    }

    public static String getRequestUrl(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String contextPath = request.getServletContext().getContextPath();
        return url.delete(url.length() - request.getRequestURI().length(), url.length()).append(contextPath).toString();
    }
}
