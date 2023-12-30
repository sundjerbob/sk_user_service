package raf.sk.sk_user_service.enumeration;


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
