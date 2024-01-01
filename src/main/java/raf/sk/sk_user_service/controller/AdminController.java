package raf.sk.sk_user_service.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.sk.sk_user_service.authorization.anotation.Authorization;
import raf.sk.sk_user_service.authorization.anotation.RequestedRecordIdentifier;
import raf.sk.sk_user_service.dto.request.CreateUserRequest;
import raf.sk.sk_user_service.dto.model.UserDto;
import raf.sk.sk_user_service.service.api.AdminServiceApi;

@RestController
@RequestMapping("/admins")
public class AdminController {


    private final AdminServiceApi adminService;


    public AdminController(AdminServiceApi adminService) {
        this.adminService = adminService;
    }

    @Authorization(
            requiredPermissions = "ALL_USER_DATA_ACCESS",
            authTokenArgName = "authorization")
    @PostMapping
    public ResponseEntity<UserDto> createAdmin(@RequestBody CreateUserRequest createUserRequest) {
        UserDto newUser = adminService.createAdmin(createUserRequest);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @Authorization(
            requiredPermissions = {"ALL_USER_DATA_ACCESS", "PERSONAL_USER_DATA_ACCESS"},
            authTokenArgName = "authorization"
    )
    @RequestedRecordIdentifier(argName = "userId")
    @PutMapping(path = "/set_disabled")
    public ResponseEntity<Boolean> setDisabled(@RequestHeader("Authorization") String authorization,
                                               @PathParam("userId") Long userId,
                                               @PathParam("disabled") Boolean disabled) {
        return new ResponseEntity<>(adminService.setDisabled(userId, disabled), HttpStatus.OK);
    }//test user_id & userId


}
