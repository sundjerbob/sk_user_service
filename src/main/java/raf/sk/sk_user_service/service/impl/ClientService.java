package raf.sk.sk_user_service.service.impl;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raf.sk.sk_user_service.dto.request.CreateUserRequest;
import raf.sk.sk_user_service.dto.response.CreateUserResponse;
import raf.sk.sk_user_service.entity_model.Client;
import raf.sk.sk_user_service.entity_model.MembershipCard;
import raf.sk.sk_user_service.entity_model.Role;
import raf.sk.sk_user_service.entity_model.User;
import raf.sk.sk_user_service.object_mapper.UserObjectMapper;
import raf.sk.sk_user_service.repository.ClientRepository;
import raf.sk.sk_user_service.repository.MemberCardRepository;
import raf.sk.sk_user_service.repository.UserRepository;
import raf.sk.sk_user_service.service.api.ClientServiceApi;

import java.util.Date;
import java.util.Optional;

import static raf.sk.sk_user_service.object_mapper.UserObjectMapper.createReqToUser;
import static raf.sk.sk_user_service.service.impl.util.PasswordHashingUtil.hashPassword;


@Service
public class ClientService implements ClientServiceApi {
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    private final MemberCardRepository memberCardRepository;

    public ClientService(ClientRepository clientRepository, UserRepository userRepository, MemberCardRepository memberCardRepository) {
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.memberCardRepository = memberCardRepository;
    }

    @Override
    @Transactional
    public CreateUserResponse createClient(@Valid CreateUserRequest createUserRequest) {

        if (createUserRequest.getRole() != Role.CLIENT)
            throw new RuntimeException("Invalid body for create client...");


        Client client = (Client) createReqToUser(createUserRequest, new Client());

        Optional<User> emailCheck = userRepository.findByEmail(createUserRequest.getEmail());

        Optional<User> usernameCheck = userRepository.findByUsername(createUserRequest.getUsername());

        if (emailCheck.isPresent())
            throw new RuntimeException("This email is already linked to an existing account.");

        if (usernameCheck.isPresent())
            throw new RuntimeException("This username is already linked to an existing account.");

        // client is initially enabled so disabled is false
        client.setDisabled(false);
        // hash the password before storing inside db
        client.setPassword(hashPassword(client.getPassword()));
        MembershipCard membershipCard1 = memberCardRepository.save(new MembershipCard().setDurationInDays(30));

        MembershipCard membershipCard2 = memberCardRepository.save(new MembershipCard().setDurationInDays(60).setStartingDate(new Date()));
        client.addMemberCard(membershipCard1);
        client.addMemberCard(membershipCard2);

        client = clientRepository.save(client);

        return new CreateUserResponse(UserObjectMapper.userToDto(client));

    }

}
