package raf.sk.sk_user_service.service.api;

import jakarta.validation.Valid;
import raf.sk.sk_user_service.dto.model.MembershipCardDto;
import raf.sk.sk_user_service.dto.request.CreateUserRequest;
import raf.sk.sk_user_service.dto.response.CreateUserResponse;
import raf.sk.sk_user_service.inter_service_comunication.UserPerks;

public interface ClientServiceApi {
    CreateUserResponse createClient(@Valid CreateUserRequest createUserRequest);

    UserPerks getUserPerks(Long userId, String gymName);

    MembershipCardDto startNewMembership(Long userId, String gymName);
}
