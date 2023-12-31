package raf.sk.sk_user_service.service.impl;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raf.sk.sk_user_service.dto.request.CreateUserRequest;
import raf.sk.sk_user_service.dto.model.UserDto;
import raf.sk.sk_user_service.entity_model.Admin;
import raf.sk.sk_user_service.entity_model.User;
import raf.sk.sk_user_service.entity_model.Role;
import raf.sk.sk_user_service.object_mapper.UserDtoMapper;
import raf.sk.sk_user_service.repository.AdminRepository;
import raf.sk.sk_user_service.repository.UserRepository;
import raf.sk.sk_user_service.service.api.AdminServiceApi;

import java.util.Optional;

import static raf.sk.sk_user_service.util.PasswordHashingUtil.hashPassword;
import static raf.sk.sk_user_service.object_mapper.UserDtoMapper.createReqToUser;


@Service
public class AdminService implements AdminServiceApi {
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public AdminService(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDto createAdmin(@Valid CreateUserRequest createUserRequest) {

        if (createUserRequest.getRole() != Role.ADMIN)
            throw new RuntimeException("Invalid body for create admin...");

        Optional<User> emailCheck = userRepository.findByEmail(createUserRequest.getEmail());

        Optional<User> usernameCheck = userRepository.findByUsername(createUserRequest.getUsername());

        if (emailCheck.isPresent())
            throw new RuntimeException("This email is already linked to an existing account.");

        if (usernameCheck.isPresent())
            throw new RuntimeException("This username is already linked to an existing account.");

        Admin admin = new Admin();

        createReqToUser(createUserRequest, admin);

        admin.setPassword(hashPassword(admin.getPassword()));
        admin.setDisabled(false);

        admin = adminRepository.save(admin);

        return UserDtoMapper.userToDto(admin);
    }

    @Override
    public boolean setDisabled(long userId, boolean isDisabled) {

        switch (userRepository.setDisabledState(userId, isDisabled)) {
            case 1 -> {
                return isDisabled;
            }
            case 0 -> throw new RuntimeException("User with an id " + userId + " has not been found...");

            default ->
                    throw new RuntimeException("Case of multiple users with id + " + userId + " shouldn't happen... 0,o'");
        }



    }

}
