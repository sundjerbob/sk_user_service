package raf.sk.sk_user_service.enumeration;

import jakarta.persistence.Entity;

import java.util.List;

public enum Role {
    GYM_MANAGER(Permissions.LOG_IN, Permissions.PERSONAL_DATA_EDIT, Permissions.SYSTEM_DATA_EDIT),
    CLIENT(Permissions.LOG_IN, Permissions.PERSONAL_DATA_EDIT),
    ADMIN(Permissions.LOG_IN, Permissions.PERSONAL_DATA_EDIT);
    Role(Permissions... permissions) {
        this.permissions = List.of(permissions);
    }

    public final List<Permissions> permissions;
}
