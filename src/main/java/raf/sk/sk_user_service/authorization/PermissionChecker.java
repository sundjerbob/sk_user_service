package raf.sk.sk_user_service.authorization;


import org.springframework.stereotype.Component;
import raf.sk.sk_user_service.authorization.Permissions;
import raf.sk.sk_user_service.authorization.service.impl.UnpackedAuthToken;
import raf.sk.sk_user_service.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PermissionChecker {

    private final UserRepository userRepository;

    private final Map<Permissions, VerifyPermitLambda> permissionHandles;


    protected final VerifyPermitLambda allDataAccess =
            auth -> auth.getRequesterRole().getPermissions().contains(Permissions.ALL_USER_DATA_ACCESS);

    protected final VerifyPermitLambda personalDataAccess = auth -> if()
    protected final VerifyPermitLambda personalDataAccess = auth -> true;


    public PermissionChecker(UserRepository userRepository) {
        this.userRepository = userRepository;
        permissionHandles = new HashMap<>();
        permissionHandles.put(Permissions.ALL_USER_DATA_ACCESS, allDataAccess);
        permissionHandles.put(Permissions.PERSONAL_USER_DATA_ACCESS, personalDataAccess);
    }






    private interface VerifyPermitLambda {
        boolean giveAccess(UnpackedAuthToken authInfo);
    }

    public boolean allowAction(UnpackedAuthToken authInfo, List<Permissions> requiredPermissions) {


    }
}
