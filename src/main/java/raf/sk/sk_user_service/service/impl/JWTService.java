package raf.sk.sk_user_service.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import raf.sk.sk_user_service.entity_model.User;
import raf.sk.sk_user_service.service.api.JWTServiceApi;

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
        Date expiresAt = new Date(issuedAt.getTime() + 24 * 60 * 60 * 1000); // datum isteka roka = trenutni datum + jedan dan u (u ms)
        Claims claims = Jwts.claims()
                .setIssuedAt(issuedAt)
                .setExpiration(expiresAt)
                .setId(user.getId().toString())
                .setSubject(user.getUsername());

        Map<String, Object> map = new HashMap<>();

        map.put("role", user.getRole().name());
        map.put("status", user.isDisabled() ? "DEACTIVATED" : "ACTIVE");
        map.put("email", user.getEmail());

        claims.putAll(map);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    @Override
    public Claims parseJWT(String jwt) {
        System.out.println(jwtSecret);
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }
}
