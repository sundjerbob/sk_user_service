package raf.sk.sk_user_service.object_mapper;

import raf.sk.sk_user_service.dto.request.CreateUserRequest;
import raf.sk.sk_user_service.dto.model.AdminDto;
import raf.sk.sk_user_service.dto.model.GymManagerDto;
import raf.sk.sk_user_service.dto.model.ClientDto;

import raf.sk.sk_user_service.dto.model.UserDto;
import raf.sk.sk_user_service.entity_model.Client;
import raf.sk.sk_user_service.entity_model.GymManager;
import raf.sk.sk_user_service.entity_model.User;

public class UserDtoMapper {


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
                    //.setDateOfEmployment(((GymManager) user).getDateOfEmployment().toString())
                    .build();


            case CLIENT -> (
                    (ClientDto.Builder)
                            new ClientDto.Builder()
                                    .email(user.getEmail())
                                    .username(user.getUsername())
                                    .firstName(user.getFirstName())
                                    .lastName(user.getLastName())
                                    .role(user.getRole()))
                    // .memberCardNumber(((Client) user).getMemberCardNumber())
                    .scheduledTrainings(((Client) user).getScheduledTrainings())
                    .build();
        };
    }

    public static void createReqToUser(CreateUserRequest createUserRequest, User user) {
        user.setEmail(createUserRequest.getEmail());
        user.setUsername(createUserRequest.getUsername());
        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        user.setPassword(createUserRequest.getPassword());
        user.setDateOfBirth(createUserRequest.getDateOfBirth());
        user.setRole(createUserRequest.getRole());
    }


}
