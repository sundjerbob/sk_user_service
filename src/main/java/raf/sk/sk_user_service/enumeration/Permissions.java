package raf.sk.sk_user_service.enumeration;

public enum Permissions {

    LOG_IN("This enables login access."),
    SYSTEM_DATA_EDIT("This enables admin to change all application system or all users personal information, and CRUD operation inside database tables."),
    PERSONAL_DATA_EDIT("This enables user to change his/hers own personal user related data.");


    public final String description;
    Permissions(String description) {
        this.description = description;
    }



}
