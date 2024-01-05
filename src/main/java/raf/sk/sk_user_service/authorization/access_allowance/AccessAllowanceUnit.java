package raf.sk.sk_user_service.authorization.access_allowance;


import org.springframework.stereotype.Component;
import raf.sk.sk_user_service.authorization.jwt_service.api.JWTServiceApi;
import raf.sk.sk_user_service.authorization.jwt_service.dto.UnpackedAuthToken;
import raf.sk.sk_user_service.authorization.perm.Permissions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static raf.sk.sk_user_service.authorization.perm.Permissions.*;

@Component
public class AccessAllowanceUnit {


    private final JWTServiceApi jwtServiceApi;

    private final Map<Permissions, PermitLambda> permissionChecks;


    public AccessAllowanceUnit(JWTServiceApi jwtServiceApi) {
        this.jwtServiceApi = jwtServiceApi;

        permissionChecks = new HashMap<>();

        permissionChecks.put(
                ALL_USER_DATA_ACCESS,
                (claims, id) ->
                        claims.getRequesterRole().getPermissions().contains(ALL_USER_DATA_ACCESS));

        permissionChecks.put(
                PERSONAL_USER_DATA_ACCESS,
                (claims, id) ->
                        claims.getRequesterRole().getPermissions().contains(PERSONAL_USER_DATA_ACCESS)
                                && id.equals(claims.getRequesterId())
        );

    }


    public boolean allowAction(String requesterClaimsToken, Long requestedRecordId, List<Permissions> requiredPermissions) {

        // Unpack the requester claims using jwtServiceApi
        UnpackedAuthToken requesterClaims = jwtServiceApi.unpackClaimsInfo(requesterClaimsToken);
        System.out.println(requestedRecordId);
        /* test */
        System.out.println(requesterClaims);

        // perform supported access permission checks for requiredPermissions
        for (Permissions reqPerm : requiredPermissions) {
            System.out.println("Permission: " + reqPerm + " acquire check for requester: " + requesterClaims.getRequesterUsername());
            // if access permission check grants access the action is allowed
            if (permissionChecks.get(reqPerm).grantAccess(requesterClaims, requestedRecordId)) {
                System.out.println("Requester: " + requesterClaims.getRequesterUsername() + " has permission: " + reqPerm + ".\nAccess is granted.");
                return true;
            }
            System.out.println("Requester: " + requesterClaims.getRequesterUsername() + " does not acquire permission: " + reqPerm + ".");
        }

        // if none of the requiredPermission checks had granted access the action is not allowed
        return false;
    }


    private interface PermitLambda {
        boolean grantAccess(UnpackedAuthToken claims, Long requestedRecordId);
    }


}
