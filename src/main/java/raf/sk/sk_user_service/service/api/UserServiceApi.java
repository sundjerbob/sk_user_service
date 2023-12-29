package raf.sk.sk_user_service.service.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import raf.sk.sk_user_service.dto.request.LoginRequest;
import raf.sk.sk_user_service.dto.response.LoginResponse;
import raf.sk.sk_user_service.dto.request.UpdateUserRequest;
import raf.sk.sk_user_service.dto.model.UserDto;

public interface UserServiceApi {

    Page<UserDto> getUsers(Pageable pageable);

    UserDto updateUser(UpdateUserRequest updateState, long id);

    LoginResponse authenticate(LoginRequest loginRequest);

}
