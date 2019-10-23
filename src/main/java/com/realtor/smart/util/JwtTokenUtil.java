package com.realtor.smart.util;

import com.realtor.smart.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.realtor.smart.util.Constants.SECRET_WORD;

// Генерируем токен на основе пользователя и пароля
//

@Component
public class JwtTokenUtil {

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;



    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("name", user.getName());
        claims.put("password", user.getPassword());

        return doGenerateToken(claims);
    }

    private String doGenerateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_WORD).compact();
    }


    public Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_WORD).parseClaimsJws(token).getBody();
    }
}
