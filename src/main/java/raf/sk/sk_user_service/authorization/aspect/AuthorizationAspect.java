package raf.sk.sk_user_service.authorization.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import raf.sk.sk_user_service.authorization.access_allowance.AccessAllowanceUnit;
import raf.sk.sk_user_service.authorization.anotation.Authorization;
import raf.sk.sk_user_service.authorization.anotation.RequestedRecordIdentifier;
import raf.sk.sk_user_service.authorization.perm.Permissions;
import raf.sk.sk_user_service.authorization.util.ArgumentExtractionUtil;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Aspect
@Configuration
public class AuthorizationAspect {

    @Value("${oauth.jwt.secret}")
    private String jwtSecret;

    private final AccessAllowanceUnit allowanceUnit;
    private final String unauthenticatedRequesterMessage = """
            Usage of this method is authorized.
            There was no requester identity data inside received Http requests Authorization header."
            Please login with your credentials, in order to gain access.
                
            """;
    private final String accessDeniedMessage = """
            Your credentials do not match authorization level required to use this method.         \s
            """;

    public AuthorizationAspect(AccessAllowanceUnit allowanceUnit) {
        this.allowanceUnit = allowanceUnit;
    }

    @Around("@annotation(authorization)") // Intercept Authorization annotation
    public Object around(ProceedingJoinPoint joinPoint, Authorization authorization) throws Throwable {

        String jwt = ArgumentExtractionUtil.getAuthArg(joinPoint, authorization.authTokenArgName());

        if (jwt == null || ! jwt.startsWith("Bearer"))
            return new ResponseEntity<>(unauthenticatedRequesterMessage, HttpStatus.UNAUTHORIZED);

        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        List<Permissions> allowedPermissions = Arrays.stream(authorization.requiredPermissions()).map(Permissions::valueOf).toList();

        RequestedRecordIdentifier recordIdentifier = method.getAnnotation(RequestedRecordIdentifier.class);


        if (allowanceUnit.allowAction(jwt, recordIdentifier == null ?
                        null : ArgumentExtractionUtil.getRequestedRecordArg(joinPoint, recordIdentifier.argName()),
                allowedPermissions
        ))
            return joinPoint.proceed();


        return new ResponseEntity<>(accessDeniedMessage, HttpStatus.FORBIDDEN);
    }


}
