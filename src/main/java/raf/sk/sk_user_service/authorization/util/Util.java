package raf.sk.sk_user_service.authorization.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import raf.sk.sk_user_service.authorization.Permissions;

import java.util.Arrays;
import java.util.List;

public class Util {
    public static String getAuthArg(ProceedingJoinPoint joinPoint, String authTokenArgName) {

        // get the jointPoint method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        // find the index of an Authentication header value argument
        int argIndex = findArgIndex(authTokenArgName, methodSignature.getParameterNames());

        //case auth arg not found
        if (argIndex == -1)
            return null;

        String authArgValue = (String) joinPoint.getArgs()[argIndex];

        // extract the token from a http request header value using Authorization Bearer token convention
        return extractBearerToken(authArgValue);
    }

    private Object getFunctionArgumentValue(ProceedingJoinPoint function, String paramName, Class<?> paramType) {

        MethodSignature methodSignature = (MethodSignature) function.getSignature();

        Class<?>[] argTypes = methodSignature.getParameterTypes();

        int argIndex = Arrays.asList(methodSignature.getParameterNames()).indexOf(paramName);

        if (argIndex == -1)
            return null;

        if (!argTypes[argIndex].isAssignableFrom(paramType))
            return null;

        return function.getArgs()[argIndex];

    }

    public static Long getRequestedRecordArg(ProceedingJoinPoint joinPoint, String reqRecArg) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Class<?>[] argTypes = methodSignature.getParameterTypes();

        int argIndex = findArgIndex(reqRecArg, methodSignature.getParameterNames());

        if (argIndex == -1)
            return null;

        String authArgValue = (String) joinPoint.getArgs()[argIndex];


    }


    /**
     * @param authHeaderValue string value of a Authorization http request header value formatted using Auth Bearer token convention.
     * @return the extracted value of a raw jwt token string,
     */
    public static String extractBearerToken(String authHeaderValue) {
        return authHeaderValue.split(" ")[1].trim();

    }

    public static List<Permissions> stringsToPermissions(String... annotatedPermissions) {
        return Arrays.stream(annotatedPermissions).map(Permissions::valueOf).toList();
    }
}
