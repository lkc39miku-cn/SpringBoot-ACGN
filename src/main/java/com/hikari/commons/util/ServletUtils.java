package com.hikari.commons.util;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * ServletUtils
 * @author lkc39miku_cn
 */
public class ServletUtils {
    public static ServletRequestAttributes servletRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    public static HttpServletRequest httpServletRequest() throws NullPointerException {
        return servletRequestAttributes().getRequest();
    }

    public static String getParameter(String name) {
        return httpServletRequest().getParameter(name);
    }
}
