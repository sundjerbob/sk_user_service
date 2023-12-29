package raf.sk.sk_user_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raf.sk.sk_user_service.dto.request.CreateUserRequest;
import raf.sk.sk_user_service.dto.model.UserDto;
import raf.sk.sk_user_service.service.api.ClientServiceApi;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientServiceApi clientService;

    public ClientController(ClientServiceApi clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<UserDto> createClient(@RequestBody CreateUserRequest createUserRequest) {

        System.out.println("ALIJA BALIJA NAJJJ");
        UserDto newUser = clientService.createClient(createUserRequest);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
