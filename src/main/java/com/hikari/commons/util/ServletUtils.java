package com.hikari.commons.util;

import com.hikari.commons.key.CharsetKey;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ServletUtils
 * @author lkc39miku_cn
 */
public class ServletUtils {

    public static void render(HttpServletResponse response, String str) throws IOException {
        response.setStatus(com.hikari.commons.enums.HttpServletResponse.SC_OK.code());
        response.setContentType("application/json");
        response.setCharacterEncoding(CharsetKey.UTF_8);
        response.getWriter().print(str);
    }
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
