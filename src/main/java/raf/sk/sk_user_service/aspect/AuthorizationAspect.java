package raf.sk.sk_user_service.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import raf.sk.sk_user_service.enumeration.Permissions;
import raf.sk.sk_user_service.jwt.impl.UnpackedAuthToken;
import raf.sk.sk_user_service.repository.UserRepository;
import raf.sk.sk_user_service.jwt.JWTServiceApi;
import raf.sk.sk_user_service.anotation.Authorization; // Import your custom annotation

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        if (authHeaderValue == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        UnpackedAuthToken authInfo = tokenService.unpackAuthInfo(authHeaderValue);
        if (checkPermissions() &&)


            // Return FORBIDDEN if the user doesn't have an appropriate role for the specified route
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }


    private String getAuthArg(ProceedingJoinPoint joinPoint, String authTokenArgName) {

        // get the jointPoint method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        // find the index of an Authentication header value argument
        int argIndex = findArgIndex(authTokenArgName, methodSignature.getParameterNames());

        // get the passed argument value
        String authArgValue = (String) joinPoint.getArgs()[argIndex];

        // extract the token from a http request header value using Authorization Bearer token convention
        return extractBearerToken(authArgValue);

    }


    /**
     * @param lookUpArg     name of an argument of witch the index is required.
     * @param argumentNames argument names list inside method signature.
     * @return an index of a lookUpArg inside the argumentNames array or -1 if there is no lookUpArg found inside argumentNames.
     */
    private int findArgIndex(String lookUpArg, String... argumentNames) {
        return Arrays.asList(argumentNames).indexOf(lookUpArg);
    }

    /**
     * @param authHeaderValue string value of a Authorization http request header value formatted using Auth Bearer token convention.
     * @return the extracted value of a raw jwt token string,
     */
    private String extractBearerToken(String authHeaderValue) {
        return authHeaderValue.split(" ")[1].trim();

    }

    private List<Permissions> parseAnnotationPermissions(String... annotatedPermissions) {
        return Arrays.stream(annotatedPermissions).map(Permissions::valueOf).toList();
    }

    private boolean checkPermissions(UnpackedAuthToken requesterInfo, List<Permissions> requiredPermissions) {
        List<>
        if (requesterInfo.getRequesterRole().getPermissions().contains(Permissions.))

    }

}
