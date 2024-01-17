package raf.sk.sk_user_service.service.impl;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raf.sk.sk_user_service.authorization.roles.Role;
import raf.sk.sk_user_service.dto.model.MembershipCardDto;
import raf.sk.sk_user_service.dto.request.CreateUserRequest;
import raf.sk.sk_user_service.dto.response.CreateUserResponse;
import raf.sk.sk_user_service.entity_model.Client;
import raf.sk.sk_user_service.entity_model.MembershipCard;
import raf.sk.sk_user_service.entity_model.User;
import raf.sk.sk_user_service.inter_service_comunication.UserPerks;
import raf.sk.sk_user_service.object_mapper.ObjectMapper;
import raf.sk.sk_user_service.repository.ClientRepository;
import raf.sk.sk_user_service.repository.MemberCardRepository;
import raf.sk.sk_user_service.repository.UserRepository;
import raf.sk.sk_user_service.service.api.ClientServiceApi;

import java.time.LocalDate;
import java.util.Optional;

import static raf.sk.sk_user_service.object_mapper.ObjectMapper.createReqToUser;
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

        client.addMemberCard(membershipCard1);

        client = clientRepository.save(client);

        return new CreateUserResponse(ObjectMapper.userToDto(client));

    }

    @Override
    public UserPerks getUserPerks(Long userId, String gymName) {

        Optional<User> clientOptional = clientRepository.findById(userId);

        if (clientOptional.isEmpty())
            return null;

        Client client = (Client) clientOptional.get();

        boolean hasMembershipInGym = false;
        int totalBookedWorkoutsInGym = 0;

        for (MembershipCard membershipCard : client.getMemberCards()) {
            if (membershipCard.getGymName().equals(gymName)) {
                if (!hasMembershipInGym)
                    hasMembershipInGym = true;
                totalBookedWorkoutsInGym += membershipCard.getBookedWorkouts();
            }
        }

        if (hasMembershipInGym)
            return new UserPerks().setBookedWorkouts(totalBookedWorkoutsInGym);
        else
            return null;
    }

    @Override
    public MembershipCardDto startNewMembership(Long userId, String gymName) {
        Optional<User> clientOptional = clientRepository.findById(userId);

        if (clientOptional.isEmpty())
            return null;

        // adding the card
        MembershipCard membershipCard = memberCardRepository.save(
                new MembershipCard().setDurationInDays(30).setStartingDate(LocalDate.now()).setGymName(gymName)
        );

        Client client = (Client) clientOptional.get();
        // linking member card to client
        client.addMemberCard(membershipCard);
        // update database
        clientRepository.save(client);


        return ObjectMapper.membershipCardToDto(membershipCard);

    }

}
