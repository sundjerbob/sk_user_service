package raf.sk.sk_user_service.service.api;

import raf.sk.sk_user_service.dto.request.CreateUserRequest;
import raf.sk.sk_user_service.dto.model.UserDto;

public interface AdminServiceApi {


    UserDto createAdmin(CreateUserRequest createUserRequest);

    boolean setDisabled(Long userId, boolean isDisabled);
}
