package com.pinson.othello.auths.jwts;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final Duration JWT_TOKEN_VALIDITY = Duration.ofHours(24);


    @Value("${token.signing.key}")
    private String signingKey;

    public String extractUsername(String token) {
        return this.extractClaim(token, Claims::getSubject);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = this.extractUsername(token);
        return (
            username.equals(userDetails.getUsername())
            &&
            !this.isTokenExpired(token)
        );
    }

    protected String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY.toMillis()))
            .signWith(this.getSigningKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    protected boolean isTokenExpired(String token) {
        return this.extractExpiration(token).before(new Date());
    }

    protected Date extractExpiration(String token) {
        return this.extractClaim(token, Claims::getExpiration);
    }

    protected <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    protected Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(this.getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    protected Key getSigningKey() {
        byte[] signingKeyBytes = Decoders.BASE64.decode(this.signingKey);
        return Keys.hmacShaKeyFor(signingKeyBytes);
    }

}
