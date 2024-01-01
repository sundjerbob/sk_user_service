package raf.sk.sk_user_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.sk.sk_user_service.dto.request.CreateUserRequest;
import raf.sk.sk_user_service.dto.model.UserDto;
import raf.sk.sk_user_service.service.api.GymManagerServiceApi;

@RestController
@RequestMapping("/gym_managers")
public class GymManagerController {

    GymManagerServiceApi gymManagerService;


    public GymManagerController(GymManagerServiceApi gymManagerService) {
        this.gymManagerService = gymManagerService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createGymManager(@RequestBody CreateUserRequest createUserRequest) {
        UserDto newUser = gymManagerService.createGymManager(createUserRequest);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


}
