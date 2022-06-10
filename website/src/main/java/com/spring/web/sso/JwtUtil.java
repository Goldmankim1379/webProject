package com.spring.web.sso;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.spring.web.vo.LoginVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JwtUtil {
	public static String generateToken(String signingKey, LoginVO loginvo) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //Json web token 방식을 사용하여 로그인 정보가 확실히 맞으면
        //사용자 주요 정보를 웹토큰으로 생성하는 부분.
        JwtBuilder builder = Jwts.builder()
                .setSubject(loginvo.getId())
                .setIssuedAt(now)
                .claim("name", loginvo.getName())
                .claim("id", loginvo.getId())          
                .claim("auth", loginvo.getAuth())
                .signWith(SignatureAlgorithm.HS256, signingKey);

        return builder.compact();
    }

    public static String getSubject(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey){
    	//Json Web 토큰의 정보를 String 타입으로 반환하는 함수
        String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        if(token == null) return null;        
        return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody().getSubject();
    }
    
    
    public static Claims getAll(HttpServletRequest httpServletRequest, String jwtTokenCookieName, String signingKey){
    	//Json Web 토큰의 정보를 Claims 타입으로 반환하는 함수
        String token = CookieUtil.getValue(httpServletRequest, jwtTokenCookieName);
        if(token == null) return null;            
		return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();        
    }
}
