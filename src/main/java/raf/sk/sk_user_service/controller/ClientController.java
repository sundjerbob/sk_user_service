package raf.sk.sk_user_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raf.sk.sk_user_service.dto.request.CreateUserRequest;
import raf.sk.sk_user_service.dto.request.NewMembershipRequest;
import raf.sk.sk_user_service.dto.response.CreateUserResponse;
import raf.sk.sk_user_service.service.api.ClientServiceApi;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientServiceApi clientService;

    public ClientController(ClientServiceApi clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createClient(@RequestBody CreateUserRequest createUserRequest) {

        return new ResponseEntity<>(clientService.createClient(createUserRequest), HttpStatus.CREATED);
    }

    @PostMapping("/aa")
    public ResponseEntity<NewMembershipRequest> startNewMembership() {
        return null;
    }

}
