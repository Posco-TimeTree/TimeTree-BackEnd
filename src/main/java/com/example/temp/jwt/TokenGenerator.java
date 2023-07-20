package com.example.temp.jwt;

import com.example.temp.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class TokenGenerator {
    private static final String SECRET_KEY = "my_secret_key";
    private static final long TOKEN_VALID_TIME = 1000L * 60;
    private static final String MEMBER_ID_CLAIM_KEY = "Id";

    public String generateToken(Long memberId) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .claim(MEMBER_ID_CLAIM_KEY, memberId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + TOKEN_VALID_TIME))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .compact();
    }

    public String getToken(Member member) {
        return generateToken(member.getId());
    }

    public Claims getClaims(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}