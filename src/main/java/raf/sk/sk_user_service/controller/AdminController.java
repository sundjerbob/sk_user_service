package raf.sk.sk_user_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raf.sk.sk_user_service.dto.interaction.CreateUserRequest;
import raf.sk.sk_user_service.dto.model.UserDto;
import raf.sk.sk_user_service.service.api.AdminServiceApi;

@RestController
@RequestMapping("/admins")
public class AdminController {


    private final AdminServiceApi adminService;

    public AdminController(AdminServiceApi adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createAdmin(@RequestBody CreateUserRequest createUserRequest) {
        UserDto newUser = adminService.createAdmin(createUserRequest);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


}
