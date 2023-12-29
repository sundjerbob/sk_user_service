package raf.sk.sk_user_service.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.sk.sk_user_service.dto.interaction.LoginRequest;
import raf.sk.sk_user_service.dto.interaction.LoginResponse;
import raf.sk.sk_user_service.dto.interaction.UpdateUserRequest;
import raf.sk.sk_user_service.dto.model.UserDto;
import raf.sk.sk_user_service.service.api.UserServiceApi;
import raf.sk.sk_user_service.anotation.Authorization;

@RestController
@RequestMapping("/users")
public class UserController {


    private final UserServiceApi userService;

    public UserController(UserServiceApi userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        return new ResponseEntity<>(userService.authenticate(loginRequest), HttpStatus.OK);
    }

    @GetMapping
    //@Authorization(roles = {"ADMIN"})
    public ResponseEntity<Page<UserDto>> getAllUsers(
      //      @RequestHeader("Authorization") String authorization,
            Pageable pageable) {
        return new ResponseEntity<>(userService.getUsers(pageable), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    @Authorization(roles = {"ADMIN", "CLIENT"})
    public ResponseEntity<UserDto> updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest updateDto) {
        return new ResponseEntity<>(userService.updateUser(updateDto, userId), HttpStatus.OK);
    }
}
