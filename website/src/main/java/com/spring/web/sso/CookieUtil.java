package com.spring.web.sso;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.WebUtils;

public class CookieUtil {

	public static void create(HttpServletResponse httpServletResponse, String name, String value, Boolean secure, Integer maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(secure);        
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
    }
	
	public static void createSimple(HttpServletResponse httpServletResponse, String name, String value, Integer maxAge){
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		httpServletResponse.addCookie(cookie);
	}
	

	public static void clear(HttpServletResponse httpServletResponse, String name) {
        Cookie cookie = new Cookie(name, null);        
        cookie.setPath("/");
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
    }
	
	public static String getValue(HttpServletRequest httpServletRequest, String name) {
        Cookie cookie = WebUtils.getCookie(httpServletRequest, name);
        return cookie != null ? cookie.getValue() : null;
    }
	
}
