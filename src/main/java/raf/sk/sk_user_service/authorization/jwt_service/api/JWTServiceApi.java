package raf.sk.sk_user_service.authorization.jwt_service.api;

import raf.sk.sk_user_service.entity_model.User;
import raf.sk.sk_user_service.authorization.jwt_service.dto.UnpackedAuthToken;


public interface JWTServiceApi {


    String generateJWT(User user);

    UnpackedAuthToken unpackClaimsInfo(String jwtAuthToken);


}
