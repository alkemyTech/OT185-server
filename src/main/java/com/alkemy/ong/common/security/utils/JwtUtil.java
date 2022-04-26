package com.alkemy.ong.common.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "4LK3MY4LK3MY4LK3MY4LK3MY4LK3MY4LK3MY4LK3MY4LK3MY4LK3MY4LK3MY4LK3MY4LK3MY4LK3MY4LK3MY";

    SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);

    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    public  Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

    }


    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails){

        Map<String,Object> claims = new HashMap<>();

        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject){

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000*60*60*10))
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }


    public Boolean validateToken(String token, UserDetails userDetails){

        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }
}
