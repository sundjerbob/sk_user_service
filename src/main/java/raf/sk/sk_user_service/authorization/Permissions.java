package raf.sk.sk_user_service.authorization;

public enum Permissions {

    ALL_USER_DATA_ACCESS("Permission used by admins and intra service communication."),


    PERSONAL_USER_DATA_ACCESS("Permission used by clients and gym managers to access and change their own personal data.");



    public final String description;
    Permissions(String description) {
        this.description = description;
    }



}
