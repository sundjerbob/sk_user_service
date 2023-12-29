package raf.sk.sk_user_service.service.api;

import jakarta.validation.Valid;
import raf.sk.sk_user_service.dto.interaction.CreateUserRequest;
import raf.sk.sk_user_service.dto.model.UserDto;

public interface ClientServiceApi {
    UserDto createClient(@Valid CreateUserRequest createUserRequest);

}
