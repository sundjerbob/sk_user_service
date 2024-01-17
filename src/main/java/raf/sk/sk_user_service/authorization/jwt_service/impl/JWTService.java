package raf.sk.sk_user_service.authorization.jwt_service.impl;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import raf.sk.sk_user_service.authorization.jwt_service.api.JWTServiceApi;
import raf.sk.sk_user_service.authorization.jwt_service.dto.UnpackedAuthToken;
import raf.sk.sk_user_service.authorization.roles.Role;
import raf.sk.sk_user_service.entity_model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class JWTService implements JWTServiceApi {

    private final String EMAIL_CLAIM_KEY = "emil";
    private final String ROLE_CLAIM_KEY = "role";
    private final String STATUS_CLAIMS_KEY = "status";


    @Value("${oauth.jwt.secret}")
    private String jwtSecret;


    @Override
    public String generateJWT(User user) {

        Date issuedAt = new Date();

        Date expiresAt = new Date(issuedAt.getTime() + 24 * 60 * 60 * 1000);

        Claims claims = Jwts.claims()
                .setIssuer(user.getUsername())
                .setId(user.getId().toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expiresAt);

        Map<String, String> map = new HashMap<>();

        map.put(EMAIL_CLAIM_KEY, user.getEmail());
        map.put(ROLE_CLAIM_KEY, user.getRole().name());
        map.put(STATUS_CLAIMS_KEY, user.isDisabled() ? "DEACTIVATED" : "ACTIVE");

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
            throw new RuntimeException("Token has expired");
        } catch (SignatureException e) {
            // Token signature validation failed (token tampered with or key issue)
            throw new RuntimeException("Token signature validation failed");
        } catch (Exception e) {
            // Other exceptions
            throw new RuntimeException("Error parsing JWT: " + e.getMessage());
        }


    }

    public UnpackedAuthToken unpackClaimsInfo(String webAuthToken) {

        Claims claims = parseJWT(webAuthToken);

        if (claims == null)
            throw new RuntimeException("Authorization token is not valid!");

        if ((new Date()).after(claims.getExpiration()))
            throw new RuntimeException("Authorization token expired, please login.");

        return new UnpackedAuthToken.Builder()
                .setRequesterId(Long.parseLong(claims.getId()))
                .setRequesterUsername(claims.getIssuer())
                .setRequesterEmail(claims.get(EMAIL_CLAIM_KEY).toString())
                .setRequesterRole(Role.valueOf((String) claims.get(ROLE_CLAIM_KEY)))
                .setDisabled(claims.get(STATUS_CLAIMS_KEY).equals("DEACTIVATED"))
                .setSessionStartTime(claims.getIssuedAt())
                .setSessionEndTime(claims.getExpiration())
                .build();


    }

}
