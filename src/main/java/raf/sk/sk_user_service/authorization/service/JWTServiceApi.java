package raf.sk.sk_user_service.authorization.service;

import raf.sk.sk_user_service.entity_model.User;
import raf.sk.sk_user_service.authorization.service.impl.UnpackedAuthToken;


public interface JWTServiceApi {


    String generateJWT(User user);

    UnpackedAuthToken unpackAuthInfo(String jwtAuthToken);


}
