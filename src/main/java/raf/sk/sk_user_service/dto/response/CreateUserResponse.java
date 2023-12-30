package raf.sk.sk_user_service.dto.response;

import raf.sk.sk_user_service.dto.model.UserDto;
public class CreateUserResponse {

    private final UserDto created;

    public CreateUserResponse(UserDto created) {
        this.created = created;
    }

    public UserDto getCreated() {
        return created;
    }
}
