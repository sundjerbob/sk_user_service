package raf.sk.sk_user_service.service.impl;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raf.sk.sk_user_service.authorization.jwt_service.api.JWTServiceApi;
import raf.sk.sk_user_service.dto.model.UserDto;
import raf.sk.sk_user_service.dto.request.LoginRequest;
import raf.sk.sk_user_service.dto.request.UpdateUserRequest;
import raf.sk.sk_user_service.dto.response.LoginResponse;
import raf.sk.sk_user_service.dto.response.UpdateUserResponse;
import raf.sk.sk_user_service.entity_model.User;
import raf.sk.sk_user_service.object_mapper.UserObjectMapper;
import raf.sk.sk_user_service.repository.UserRepository;
import raf.sk.sk_user_service.service.api.UserServiceApi;

import java.util.Optional;

import static raf.sk.sk_user_service.object_mapper.UserObjectMapper.userToDto;
import static raf.sk.sk_user_service.service.impl.util.PasswordHashingUtil.matchRawAndHashed;


@Service
@Transactional
public class UserService implements UserServiceApi {

    private final UserRepository userRepository;
    private final JWTServiceApi jwtService;


    public UserService(UserRepository userRepository, JWTServiceApi jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public Page<UserDto> getUsers(@Valid Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(UserObjectMapper::userToDto);
    }


    @Override
    public UpdateUserResponse updateUser(long id, UpdateUserRequest updateState) {

        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty())
            return new UpdateUserResponse(null);

        User user = userOptional.get();

        if (!user.getEmail().equals(updateState.getEmail()))
            user.setEmail(updateState.getEmail());

        if (!user.getUsername().equals(updateState.getUsername()))
            user.setUsername(updateState.getUsername());

        if (!user.getFirstName().equals(updateState.getFirstName()))
            user.setFirstName(updateState.getFirstName());

        if (!user.getLastName().equals(updateState.getLastName()))
            user.setLastName(updateState.getLastName());

        user = userRepository.save(user);

        return new UpdateUserResponse(userToDto(user));
    }

    @Override
    public LoginResponse authenticate(LoginRequest loginRequest) {

        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Verify the hashed password
            if (matchRawAndHashed(loginRequest.getPassword(), user.getPassword())) {
                // Passwords match, proceed with authentication and token generation\

                return new LoginResponse().setJwt(jwtService.generateJWT(user));
            }
        }

        // Authentication failed
        return null;
    }


}