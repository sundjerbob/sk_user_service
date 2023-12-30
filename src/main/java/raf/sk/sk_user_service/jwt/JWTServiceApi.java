package raf.sk.sk_user_service.jwt;

import raf.sk.sk_user_service.entity_model.User;
import raf.sk.sk_user_service.jwt.impl.UnpackedAuthToken;


public interface JWTServiceApi {


    String generateJWT(User user);

    UnpackedAuthToken unpackAuthInfo(String jwtAuthToken);


}
