package raf.sk.sk_user_service.service.api;

public interface PasswordHashingServiceApi {
    String hashPassword(String rawPassword);

    boolean matches(String rawPassword, String hashedPassword);
}
