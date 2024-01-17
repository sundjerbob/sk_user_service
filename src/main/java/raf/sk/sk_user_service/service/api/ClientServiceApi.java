package raf.sk.sk_user_service.service.api;

import raf.sk.sk_user_service.dto.model.MembershipCardDto;
import raf.sk.sk_user_service.dto.request.CreateUserRequest;
import raf.sk.sk_user_service.dto.response.CreateUserResponse;
import raf.sk.sk_user_service.inter_service_comunication.UserPerksDto;

public interface ClientServiceApi {
    CreateUserResponse createClient(CreateUserRequest createUserRequest);

    UserPerksDto getUserPerks(Long userId, String gymName);

    MembershipCardDto startNewMembership(Long userId, String gymName);

    boolean incrementBookedWorkouts(Long clientId, String gymName);
}
