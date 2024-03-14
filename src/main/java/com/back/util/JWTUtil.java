package com.back.util;


import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTUtil {

    private static String key = "1234567890123456789012345678901234567890";

    public static String generateToken(Map<String, Object> valueMap, int min){

        SecretKey key = null;

        try{// 비밀 키를 생성합니다. 이 키는 JWT를 서명하는 데 사용됩니다.
            key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));

        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }

        String jwtStr = Jwts.builder()//JWT를 생성합니다.
                //헤더에 "typ"이라는 키와 "JWT"라는 값을 설정
                .setHeader(Map.of("typ","JWT"))
                // 주어진 valueMap을 사용하여 클레임을 설정합니다. 클레임은 JWT의 페이로드에 포함되는 정보입니다.
                .setClaims(valueMap)
                //JWT의 발행 시간을 현재 시간으로 설정
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                //JWT의 만료 시간을 현재 시간으로부터 min(받아온값)분 후로 설정
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
                //signWith(key)를 사용하여 비밀 키로 JWT를 서명하고, compact()를 사용하여 JWT를 압축
                .signWith(key)
                //압축된 JWT를 문자열로 반환
                .compact();

        return jwtStr;
    }
    // 주어진 JSON 웹 토큰(JWT)을 검증하는 역할
    public static Map<String, Object> validateToken(String token) {

        Map<String, Object> claim = null;

        try{

            SecretKey key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes("UTF-8"));

            // JWT를 파싱하고 검증
            claim = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token) // 파싱 및 검증, 실패 시 에러
                    .getBody();

        }catch(MalformedJwtException malformedJwtException){
            throw new CustomJWTException("MalFormed");
        }catch(ExpiredJwtException expiredJwtException){
            throw new CustomJWTException("Expired");
        }catch(InvalidClaimException invalidClaimException){
            throw new CustomJWTException("Invalid");
        }catch(JwtException jwtException){
            throw new CustomJWTException("JWTError");
        }catch(Exception e){
            throw new CustomJWTException("Error");
        }
        return claim;
    }

}