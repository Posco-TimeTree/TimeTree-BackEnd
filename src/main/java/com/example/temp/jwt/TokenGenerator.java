package com.example.temp.jwt;

import com.example.temp.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class TokenGenerator {
    private static final String secretKey = "sercretKey";
    private static final long validityInMilliseconds = 1000L * 60;
    private static final String MEMBER_ID_CLAIM_KEY = "Id";

    private String generateToken(String id, String email) {
        return Jwts.builder()
                .setSubject(id)
                .claim("email", email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getToken(Member member) {
        return generateToken(String.valueOf(member.getId()), member.getEmail());
    }

    public Claims getClaims(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}