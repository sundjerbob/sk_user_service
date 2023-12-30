package raf.sk.sk_user_service.dto.response;

import raf.sk.sk_user_service.dto.model.UserDto;

public class UpdateUserResponse {

    private final UserDto updatedValue;

    public UpdateUserResponse(UserDto updatedValue)throws RuntimeException {
        this.updatedValue = updatedValue;
    }

    public UserDto getUpdatedValue() {
        return updatedValue;
    }
}
