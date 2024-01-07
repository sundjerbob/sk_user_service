package raf.sk.sk_user_service.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.sk.sk_user_service.authorization.anotation.Authorization;
import raf.sk.sk_user_service.authorization.anotation.RequestedRecordIdentifier;
import raf.sk.sk_user_service.dto.model.UserDto;
import raf.sk.sk_user_service.dto.request.LoginRequest;
import raf.sk.sk_user_service.dto.request.UpdateUserRequest;
import raf.sk.sk_user_service.dto.response.LoginResponse;
import raf.sk.sk_user_service.dto.response.UpdateUserResponse;
import raf.sk.sk_user_service.service.api.UserServiceApi;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceApi userService;

    public UserController(UserServiceApi userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = userService.authenticate(loginRequest);
        if (loginResponse == null) {
            return new ResponseEntity<>("User credentials doesnt match any user from the database.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    //@Authorization(requiredPermissions = "ALL_USER_DATA_ACCESS", authTokenArgName = "authorization")
    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUsers(
            @RequestHeader("Authorization") String authorization,
            Pageable pageable) {
        return new ResponseEntity<>(userService.getUsers(pageable), HttpStatus.OK);
    }


    @Authorization(
            requiredPermissions = {"ALL_USER_DATA_ACCESS", "PERSONAL_USER_DATA_ACCESS"},
            authTokenArgName = "authorization"
    )
    @RequestedRecordIdentifier(argName = "userId")
    @PutMapping("/{userId}/update")
    public ResponseEntity<UpdateUserResponse> updateUser(@RequestHeader("Authorization") String authorization,
                                                         @PathVariable("userId") Long userId,
                                                         @RequestBody UpdateUserRequest updateDto) {

        UpdateUserResponse response = userService.updateUser(userId, updateDto);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }


}
