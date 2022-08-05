package com.hikari.commons.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Slf4j
public class JwtUtils {
    public static Long JWT_TTL = 60 * 60 * 1000L;

    @Value("${jwt.secret}")
    public static String JWT_KEY = "asdw";

    @Value("${jwt.header}")
    private String header;

    public static String createJwt(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, IdUtils.uuid());
        return builder.compact();
    }

    public static String createJwt(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, IdUtils.uuid());
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();

        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JWT_TTL;
        }

        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        return Jwts.builder()
                .setId(uuid)
                .setSubject(subject)
                .setIssuer("lkc39miku_cn")
                .setIssuedAt(now)
                .signWith(signatureAlgorithm, secretKey)
                .setExpiration(expDate);
    }

    public static String createJwt(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);
        return builder.compact();
    }


    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1YTJiODI2YWU4YjI0NzhiYjk3ZWU1OTNlOWQ3M2YxNiIsInN1YiI6IjEiLCJpc3MiOiJkZW1vIiwiaWF0IjoxNjU2Njg0NTg2LCJleHAiOjE2NTY2ODgxODZ9.Ej45D16DXHC1xXPzhOVBn78tOlnB8aY9EgtQdUZVxk0";
        Claims claims = parseJwt(token);
        log.info("claims: {}", claims);
    }

    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    public static Claims parseJwt(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}