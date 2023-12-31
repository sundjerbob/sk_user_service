package raf.sk.sk_user_service.authorization.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import raf.sk.sk_user_service.authorization.anotation.Authorization;
import raf.sk.sk_user_service.authorization.Permissions;
import raf.sk.sk_user_service.authorization.service.impl.UnpackedAuthToken;
import raf.sk.sk_user_service.repository.UserRepository;
import raf.sk.sk_user_service.authorization.service.JWTServiceApi;

import java.util.Arrays;
import java.util.List;

import static raf.sk.sk_user_service.authorization.util.Util.*;

@Aspect
@Configuration
public class AuthorizationAspect {

    @Value("${oauth.jwt.secret}")
    private String jwtSecret;

    private final JWTServiceApi tokenService;
    private final UserRepository userRepository;

    public AuthorizationAspect(JWTServiceApi tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    @Around("@annotation(authorization)") // Intercept Authorization annotation
    public Object around(ProceedingJoinPoint joinPoint, Authorization authorization) throws Throwable {

        System.out.println(authorization.authTokenArgName()); //test

        String authHeaderValue = getAuthArg(joinPoint, authorization.authTokenArgName());

        // there is no auth argument, no session cookie no access
        if (authHeaderValue == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        UnpackedAuthToken authInfo = tokenService.unpackAuthInfo(authHeaderValue);


        List<Permissions> requiredPermissions = stringsToPermissions(authorization.requiredPermissions());

        if(requiredPermissions.isEmpty())
            return joinPoint.proceed();

        if (performAuthorization(authInfo, requiredPermissions))
            return joinPoint.proceed();

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }






    private boolean performAuthorization(UnpackedAuthToken requesterInfo, List<Permissions> requiredPermissions) {

        return false;
    }

}
