package raf.sk.sk_user_service.authorization.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import raf.sk.sk_user_service.authorization.anotation.Authorization;
import raf.sk.sk_user_service.repository.UserRepository;
import raf.sk.sk_user_service.authorization.jwt_service.JWTServiceApi;

@Aspect
@Configuration
public class AuthorizationAspect {

    @Value("${oauth.jwt.secret}")
    private String jwtSecret;



    public AuthorizationAspect(JWTServiceApi tokenService, UserRepository userRepository) {
    }

    @Around("@annotation(authorization)") // Intercept Authorization annotation
    public Object around(ProceedingJoinPoint joinPoint, Authorization authorization) throws Throwable {

        return joinPoint.proceed();
    }







}
