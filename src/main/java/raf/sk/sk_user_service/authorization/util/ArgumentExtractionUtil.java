package raf.sk.sk_user_service.authorization.util;

import jakarta.annotation.Nullable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import raf.sk.sk_user_service.authorization.perm.Permissions;

import java.util.Arrays;
import java.util.List;

public class ArgumentExtractionUtil {

    public static String getAuthArg(ProceedingJoinPoint joinPoint, String authTokenArgName) {

        Object authArgValue = getFunctionArgumentValue(joinPoint, authTokenArgName, String.class);

        return authArgValue instanceof String ? authTokenArgName : null;
    }


    public static @Nullable Long getRequestedRecordArg(ProceedingJoinPoint joinPoint, String requestedRecordArgName) {


        Object argValue = getFunctionArgumentValue(joinPoint, requestedRecordArgName, Long.class);

        return argValue instanceof Long ? (long) argValue : null;

    }

    private static Object getFunctionArgumentValue(ProceedingJoinPoint function, String paramName, Class<?> paramType) {

        MethodSignature methodSignature = (MethodSignature) function.getSignature();

        Class<?>[] argTypes = methodSignature.getParameterTypes();

        int argIndex = Arrays.asList(methodSignature.getParameterNames()).indexOf(paramName);

        if (argIndex == -1)
            throw new RuntimeException("Message from Authorization.Util\nFunction argument with name: " + paramName + " could not be found inside the function " + function.getSignature().getName() + "...");


        if (!argTypes[argIndex].isAssignableFrom(paramType))
            throw new RuntimeException("Message from Authorization.Util\nFunction argument with name: " + paramName + " is not Assignable from type " + paramType.getSimpleName() + "...");

        if (paramType == Integer.class)
            return null;

        return function.getArgs()[argIndex];

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
