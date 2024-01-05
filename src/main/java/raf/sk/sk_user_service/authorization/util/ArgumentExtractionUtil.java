package raf.sk.sk_user_service.authorization.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.stream;

public class ArgumentExtractionUtil {

    public static String getAuthArg(ProceedingJoinPoint joinPoint, String authTokenArgName) {

        String authArgValue = (String) getFunctionArgumentValue(joinPoint, authTokenArgName, String.class);
        return authArgValue != null && authArgValue.startsWith("Bearer ") ? authArgValue.replaceFirst("Bearer +", "").trim() : null;
    }


    public static Long getRequestedRecordArg(ProceedingJoinPoint joinPoint, String requestedRecordArgName) {
        Object argValue = getFunctionArgumentValue(joinPoint, requestedRecordArgName, Long.class);
        return Long.parseLong(argValue.toString());
    }

    private static Object getFunctionArgumentValue(ProceedingJoinPoint function, String paramName, Class<?> paramType) {

        MethodSignature methodSignature = (MethodSignature) function.getSignature();

        List<Object>  testList = Arrays.stream(function.getArgs()).toList();
        System.out.println("DEbugingg "  + testList);

        Class<?>[] argTypes = methodSignature.getParameterTypes();

        int argIndex = Arrays.asList(methodSignature.getParameterNames()).indexOf(paramName);

        if (argIndex == -1) {
            System.out.println("Message from ArgumentExtractionUtil\nFunction argument with name: " + paramName + " could not be found inside the function " + function.getSignature().getName() + "...");
        }

        if (!argTypes[argIndex].isAssignableFrom(paramType)) {
            System.out.println("Message from ArgumentExtractionUtil\nFunction argument with name: " + paramName + " is not Assignable from type " + paramType.getSimpleName() + "...");
        }
        return function.getArgs()[argIndex];
    }


}
