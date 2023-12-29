package raf.sk.sk_user_service.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;

import io.jsonwebtoken.Claims;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import raf.sk.sk_user_service.service.api.JWTServiceApi;
import raf.sk.sk_user_service.anotation.Authorization; // Import your custom annotation

@Aspect
@Configuration
public class AuthorizationAspect {

    @Value("${oauth.jwt.secret}")
    private String jwtSecret;

    private final JWTServiceApi tokenService;

    public AuthorizationAspect(JWTServiceApi tokenService) {
        this.tokenService = tokenService;
    }

    @Around("@annotation(authorization)") // Use your custom annotation
    public Object around(ProceedingJoinPoint joinPoint, Authorization authorization) throws Throwable {
        // Get method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        // Check for authorization parameter
        String token = null;

        for (int i = 0; i < methodSignature.getParameterNames().length; i++) {
            if (methodSignature.getParameterNames()[i].equals("authorization")) {

                // Check bearer schema
                if (joinPoint.getArgs()[i].toString().startsWith("Bearer")) {
                    // Get token
                    token = joinPoint.getArgs()[i].toString().split(" ")[1].trim();

                    // System.out.println(token);
                }
            }
        }

        // If token is not present, return UNAUTHORIZED response
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        System.out.println(token);
        Claims claims = tokenService.parseJWT(token);


        // If fails, return UNAUTHORIZED response
        if (claims == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Check user role and proceed if the user has an appropriate role for the specified route
        String role = claims.get("role", String.class);
        if (Arrays.asList(authorization.roles()).contains(role)) {
            return joinPoint.proceed();
        }

        // Return FORBIDDEN if the user doesn't have an appropriate role for the specified route
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
