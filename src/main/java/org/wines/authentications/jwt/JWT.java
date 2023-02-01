package org.wines.authentications.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.wines.authentications.models.User;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class JWT {

    @Value("${jwt.secret.key}")
    private String secret_key;

    public String generateToken(User userDetails) {
        return generateToken(new HashMap<>(),userDetails);
    }
    public String generateToken(Map<String,Object> claims, User userDetails) {
        return Jwts.builder()
                .signWith(getSignedKey())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
                .setSubject(userDetails.getEmail())
                .compact();
    }

    public boolean isValidToken(String jwtToken,User user) {
        String userMail = extractEmail(jwtToken);
        return (userMail.equals(user.getEmail()) && !isTokenExpired(jwtToken));
    }

    public boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }
    public Date extractExpiration(String jwtToken) {
        return extractClaims(jwtToken,Claims::getExpiration);
    }

    public String extractEmail(String jwtToken) {
        return extractClaims(jwtToken,Claims::getSubject);
    }

    public <T> T extractClaims(String jwtToken, Function<Claims,T> claimsTFunction ) {
        Claims claims = getClaims(jwtToken);
        return claimsTFunction.apply(claims);
    }

    public Claims getClaims(String  jwtToken) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignedKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
    }

    public Key getSignedKey() {
        return Keys.hmacShaKeyFor(secret_key.getBytes());
    }

}
