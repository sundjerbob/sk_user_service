package raf.sk.sk_user_service.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public ResponseEntity<UserDto> createAdmin(@RequestBody CreateUserRequest createUserRequest) {
        UserDto newUser = adminService.createAdmin(createUserRequest);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping(path = "/set_disabled")
    public ResponseEntity<Boolean> setDisabled(@PathParam("user_id") Long user_id, @PathParam("disabled") Boolean disabled) {
        return new ResponseEntity<>(adminService.setDisabled(user_id, disabled), HttpStatus.OK);
    }


}
