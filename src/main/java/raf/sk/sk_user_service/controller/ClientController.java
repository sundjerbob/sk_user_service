package raf.sk.sk_user_service.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raf.sk.sk_user_service.dto.model.MembershipCardDto;
import raf.sk.sk_user_service.dto.request.CreateUserRequest;
import raf.sk.sk_user_service.dto.response.CreateUserResponse;
import raf.sk.sk_user_service.inter_service_comunication.UserPerks;
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


    @PostMapping("/startMembership")
    public ResponseEntity<MembershipCardDto> startNewMembership(
            @PathParam("userId") Long userId,
            @PathParam("gymName") String gymName,
            @RequestHeader("Authorization") String authorization) {
        MembershipCardDto newCard = clientService.startNewMembership(userId, gymName);
        return newCard != null ? ResponseEntity.ok(newCard) : ResponseEntity.notFound().build();
    }

    @GetMapping("/getPerks")
    public ResponseEntity<UserPerks> getPerks(@PathParam("userId") Long userId,
                                              @PathParam("gymName") String gymName,
                                              @RequestHeader("Authorization") String authorization) {

        UserPerks userPerks = clientService.getUserPerks(userId, gymName);
        return userPerks != null ? ResponseEntity.ok(userPerks) : ResponseEntity.notFound().build();
    }

}