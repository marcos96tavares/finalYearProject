package com.example.Api_Gateway.filter;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;

/**
 * JwtTokenProvider is a utility class that provides methods for generating and validating JWT tokens.
 * It uses a secret key to sign and verify the tokens.
 * 
 * The secret key is injected from the application properties using the @Value annotation.
 * 
 * Methods:
 * - getUsername(String token): Extracts the username from the given JWT token.
 * - validateToken(String token): Validates the given JWT token.
 * 
 * Dependencies:
 * - io.jsonwebtoken.Jwts
 * - io.jsonwebtoken.security.Keys
 * - org.springframework.beans.factory.annotation.Value
 * - org.springframework.stereotype.Component
 */
@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
