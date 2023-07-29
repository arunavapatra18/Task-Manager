package dev.arunava.taskmanager.Task.Manager.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    /**
     * Used to ensure the integrity of the token
     */
    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${jwtExpirationMs}")
    private long jwtExpirationMs;

    /**
     * Generate a new JWT based on the provided UserDetails
     * @param userDetails The UserDetails
     * @return The JWT string
     */
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    /**
     * Extract the username(email) from a token
     * @param token The token
     * @return The username
     */
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
