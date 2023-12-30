package raf.sk.sk_user_service.jwt.impl;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import raf.sk.sk_user_service.entity_model.User;
import raf.sk.sk_user_service.jwt.JWTServiceApi;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class JWTService implements JWTServiceApi {

    @Value("${oauth.jwt.secret}")
    private String jwtSecret;


    @Override
    public String generateJWT(User user) {

        Date issuedAt = new Date();

        Date expiresAt = new Date(issuedAt.getTime() + 24 * 60 * 60 * 1000);

        Claims claims = Jwts.claims()
                .setIssuer(user.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiresAt)
                .setId(user.getId().toString());

        Map<String, Object> map = new HashMap<>();

        map.put("role", user.getRole().name());
        map.put("status", user.isDisabled() ? "DEACTIVATED" : "ACTIVE");

        claims.putAll(map);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    private Claims parseJWT(String jwt) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (ExpiredJwtException e) {
            // Token has expired
            System.out.println("Token has expired");
        } catch (SignatureException e) {
            // Token signature validation failed (token tampered with or key issue)
            System.out.println("Token signature validation failed");
        } catch (Exception e) {
            // Other exceptions
            System.out.println("Error parsing JWT: " + e.getMessage());
        }

        return null;
    }

    public UnpackedAuthToken unpackAuthInfo(String webAuthToken) {

        Claims claims = parseJWT(webAuthToken);

        if (claims == null)
            throw new RuntimeException("Non valid authorization token.");

        UnpackedAuthToken unpackedAuthToken =
                new UnpackedAuthToken.Builder()
                        .setRequesterUsername(claims.getIssuer())
                        .setSessionStartTime(claims.getIssuedAt())
                        .setSessionEndTime(claims.getExpiration())
                        .build();

        if((new Date()).after(unpackedAuthToken.getSessionEnd()))
            throw new RuntimeException("Authorization token expired, please login.");

        return unpackedAuthToken;
    }

}
