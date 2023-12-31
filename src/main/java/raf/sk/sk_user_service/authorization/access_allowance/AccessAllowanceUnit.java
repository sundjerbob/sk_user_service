package raf.sk.sk_user_service.authorization.access_allowance;


import org.springframework.stereotype.Component;
import raf.sk.sk_user_service.authorization.jwt_service.JWTServiceApi;
import raf.sk.sk_user_service.authorization.jwt_service.impl.UnpackedAuthToken;
import raf.sk.sk_user_service.authorization.perm.Permissions;
import raf.sk.sk_user_service.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

import static raf.sk.sk_user_service.authorization.perm.Permissions.*;

@Component
public class AccessAllowanceUnit {


    private final JWTServiceApi jwtServiceApi;

    private final Map<Permissions, PermitLambda> permissionChecks;


    public AccessAllowanceUnit(JWTServiceApi jwtServiceApi) {
        this.jwtServiceApi = jwtServiceApi;

        permissionChecks = new HashMap<>();

        permissionChecks.put(ALL_USER_DATA_ACCESS,
                (claims, id) -> claims.getRequesterRole().getPermissions().contains(ALL_USER_DATA_ACCESS));

        permissionChecks.put(PERSONAL_USER_DATA_ACCESS,
                (claims, id) -> claims.getRequesterRole().getPermissions().contains(PERSONAL_USER_DATA_ACCESS));
    }


    public boolean allowAction(String requesterClaimsToken, Long requestedRecordId, String[] requiredPermissions) {

        // Unpack the requester claims using jwtServiceApi
        UnpackedAuthToken requesterClaims = jwtServiceApi.unpackClaimsInfo(requesterClaimsToken);

        // perform supported access permission checks for requiredPermissions
        for (String reqPerm : requiredPermissions) {

            // if access permission check grants access the action is allowed
            if (permissionChecks.get(Permissions.valueOf(reqPerm)).grantAccess(requesterClaims, requestedRecordId))
                return true;
        }

        // if none of the requiredPermission checks had granted access the action is not allowed
        return false;
    }


    private interface PermitLambda {
        boolean grantAccess(UnpackedAuthToken claims, long requestedRecordId);
    }


}
