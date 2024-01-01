package raf.sk.sk_user_service.authorization.util;

import jakarta.annotation.Nullable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;

public class ArgumentExtractionUtil {

    public static String getAuthArg(ProceedingJoinPoint joinPoint, String authTokenArgName) {

        String authArgValue = (String) getFunctionArgumentValue(joinPoint, authTokenArgName, String.class);
        System.out.println(authArgValue);
        return authArgValue != null ? extractBearerToken(authArgValue) : null;
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

        return function.getArgs()[argIndex];

    }


    private static String extractBearerToken(String authHeaderValue) {
        System.out.println(" OVO JE ARGUMENT" + authHeaderValue);
        String[] split = authHeaderValue.split("[\n\t +]");
        if (split.length < 2)
            return split[0].trim();
        return split[1].trim();

    }


}
