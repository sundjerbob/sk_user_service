package raf.sk.sk_user_service.dto.response;

import raf.sk.sk_user_service.dto.model.UserDto;

public class UpdateUserResponse {

    private UserDto updatedValue;
    private String message;

    private boolean isSuccess;

    public UpdateUserResponse(UserDto updatedValue) {
        this.updatedValue = updatedValue;
        isSuccess = updatedValue == null;
        message = updatedValue == null ?
                "User with specified id was not found!" : "Users data successfully updated!";
    }

    public UserDto getUpdatedValue() {
        return updatedValue;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
