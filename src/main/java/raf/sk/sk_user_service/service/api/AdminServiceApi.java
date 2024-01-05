package raf.sk.sk_user_service.service.api;

import jakarta.validation.Valid;
import raf.sk.sk_user_service.dto.request.CreateUserRequest;
import raf.sk.sk_user_service.dto.model.UserDto;

public interface AdminServiceApi {


    UserDto createAdmin(@Valid CreateUserRequest createUserRequest);

    boolean setDisabled(Long userId, boolean isDisabled);
}
