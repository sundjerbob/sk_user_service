package raf.sk.sk_user_service.authorization.anotation;

public @interface RequestedRecordId {

    long id();

    String username();


    Class<?> recordIdType() default Long.class;


}
