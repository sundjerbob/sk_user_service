package raf.sk.sk_user_service.object_mapper;

import raf.sk.sk_user_service.dto.model.*;
import raf.sk.sk_user_service.dto.request.CreateUserRequest;
import raf.sk.sk_user_service.entity_model.Client;
import raf.sk.sk_user_service.entity_model.GymManager;
import raf.sk.sk_user_service.entity_model.MembershipCard;
import raf.sk.sk_user_service.entity_model.User;

import java.util.List;

public class ObjectMapper {


    public static UserDto userToDto(User user) {

        return switch (user.getRole()) {
            case ADMIN -> (
                    (AdminDto.Builder)
                            new AdminDto.Builder()
                                    .email(user.getEmail())
                                    .username(user.getUsername())
                                    .firstName(user.getFirstName())
                                    .lastName(user.getLastName())
                                    .role(user.getRole()))
                    .build();


            case GYM_MANAGER -> (
                    (GymManagerDto.Builder)
                            new GymManagerDto.Builder()
                                    .email(user.getEmail())
                                    .username(user.getUsername())
                                    .firstName(user.getFirstName())
                                    .lastName(user.getLastName())
                                    .role(user.getRole()))

                    .gymName(((GymManager) user).getGymName())
                    .setDateOfEmployment(((GymManager) user).getDateOfEmployment().toString())
                    .build();


            case CLIENT -> (
                    (ClientDto.Builder)
                            new ClientDto.Builder()
                                    .email(user.getEmail())
                                    .username(user.getUsername())
                                    .firstName(user.getFirstName())
                                    .lastName(user.getLastName())
                                    .role(user.getRole()))
                    .memberCards(membershipCardsToCardsDto(((Client) user).getMembershipCards()))
                    .build();
        };
    }

    private static List<MembershipCardDto> membershipCardsToCardsDto(List<MembershipCard> cards) {
        return cards.stream().map(ObjectMapper::membershipCardToDto).toList();
    }


    public static User createReqToUser(CreateUserRequest createUserRequest, User user) {
        user.setEmail(createUserRequest.getEmail());
        user.setUsername(createUserRequest.getUsername());
        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        user.setPassword(createUserRequest.getPassword());
        user.setDateOfBirth(createUserRequest.getDateOfBirth());
        user.setRole(createUserRequest.getRole());
        return user;
    }

    public static MembershipCardDto membershipCardToDto(MembershipCard membershipCard) {
        MembershipCardDto membershipCardDto = new MembershipCardDto();
        membershipCardDto.setGymName(membershipCard.getGymName());
        membershipCardDto.setDurationInDays(membershipCard.getDurationInDays());
        membershipCardDto.setStartingDate(membershipCard.getStartingDate());
        return membershipCardDto;
    }

}
