package raf.sk.sk_user_service.service.api;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;
import raf.sk.sk_user_service.entity_model.User;


public interface JWTServiceApi {


    String generateJWT(User user);

    Claims parseJWT(String jwt);

}
