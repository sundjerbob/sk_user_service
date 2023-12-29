package raf.sk.sk_user_service.dto.interaction;

import raf.sk.sk_user_service.dto.model.UserDto;
public class CreateUserResponse {

    private final UserDto created;

    public CreateUserResponse(UserDto created) {
        this.created = created;
    }



}
