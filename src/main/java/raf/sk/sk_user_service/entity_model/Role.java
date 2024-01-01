package raf.sk.sk_user_service.entity_model;


import raf.sk.sk_user_service.authorization.perm.Permissions;

import java.util.List;

public enum Role {
    GYM_MANAGER(Permissions.PERSONAL_USER_DATA_ACCESS),
    CLIENT(Permissions.PERSONAL_USER_DATA_ACCESS),
    ADMIN(Permissions.ALL_USER_DATA_ACCESS, Permissions.PERSONAL_USER_DATA_ACCESS);

    Role(Permissions... permissions) {
        this.permissions = List.of(permissions);
    }

    private final List<Permissions> permissions;

    public List<Permissions> getPermissions() {
        return permissions;
    }
}
