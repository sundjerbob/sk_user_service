package raf.sk.sk_user_service.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raf.sk.sk_user_service.dto.request.CreateUserRequest;
import raf.sk.sk_user_service.dto.model.UserDto;
import raf.sk.sk_user_service.entity_model.GymManager;
import raf.sk.sk_user_service.entity_model.User;
import raf.sk.sk_user_service.entity_model.Role;
import raf.sk.sk_user_service.object_mapper.UserObjectMapper;
import raf.sk.sk_user_service.repository.GymManagerRepository;
import raf.sk.sk_user_service.repository.UserRepository;
import raf.sk.sk_user_service.service.api.GymManagerServiceApi;

import java.util.Optional;

import static raf.sk.sk_user_service.service.impl.util.PasswordHashingUtil.hashPassword;
import static raf.sk.sk_user_service.object_mapper.UserObjectMapper.createReqToUser;


@Service
public class GymManagerService implements GymManagerServiceApi {


    private final GymManagerRepository gymManagerRepository;
    private final UserRepository userRepository;

    public GymManagerService(GymManagerRepository gymManagerRepository, UserRepository userRepository) {
        this.gymManagerRepository = gymManagerRepository;
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public UserDto createGymManager(CreateUserRequest createUserRequest) {


        if (createUserRequest.getRole() != Role.GYM_MANAGER)
            throw new RuntimeException("Invalid request gym_manager creation...");
        GymManager gymManager = new GymManager();
        createReqToUser(createUserRequest, gymManager);

        Optional<User> emailCheck = userRepository.findByEmail(createUserRequest.getEmail());
        Optional<User> usernameCheck = userRepository.findByUsername(createUserRequest.getUsername());

        if (emailCheck.isPresent())
            throw new RuntimeException("This email is already linked to an existing account.");

        if (usernameCheck.isPresent())
            throw new RuntimeException("This username is already linked to an existing account.");

        gymManager.setPassword(hashPassword(gymManager.getPassword()));

        gymManager.setDisabled(false);

        gymManager = gymManagerRepository.save(gymManager);

        return UserObjectMapper.userToDto(gymManager);
    }

}
