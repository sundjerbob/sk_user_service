package raf.sk.sk_user_service.authorization.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {

    String[] requiredPermissions() default {"ALL_USER_DATA_ACCESS"};

    String authTokenArgName() default "authorization";




}
