package raf.sk.sk_user_service.entity_model;


import raf.sk.sk_user_service.authorization.perm.Permissions;

import java.util.List;

public enum Role {
    GYM_MANAGER(),
    CLIENT(),
    ADMIN();

    Role(Permissions... permissions) {
        this.permissions = List.of(permissions);
    }

    private final List<Permissions> permissions;

    public List<Permissions> getPermissions() {
        return permissions;
    }
}
