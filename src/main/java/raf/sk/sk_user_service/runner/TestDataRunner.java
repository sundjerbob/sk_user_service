package raf.sk.sk_user_service.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import raf.sk.sk_user_service.dto.model.UserDto;
import raf.sk.sk_user_service.dto.request.CreateUserRequest;
import raf.sk.sk_user_service.entity_model.Client;
import raf.sk.sk_user_service.entity_model.Role;
import raf.sk.sk_user_service.service.api.AdminServiceApi;
import raf.sk.sk_user_service.service.api.ClientServiceApi;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private final AdminServiceApi adminService;
    private final ClientServiceApi clientService;

    public TestDataRunner(AdminServiceApi adminService, ClientServiceApi clientService) {
        this.adminService = adminService;
        this.clientService = clientService;
    }

    @Override
    public void run(String... args) {

        adminService.createAdmin(
                createUser("mion@gmail.com", "milanche99", "Milan", "Majer", "123", Role.ADMIN)
        );

        adminService.createAdmin(
                createUser("gay@gmail.com", "Dragay", "Mihajlo", "Randjelovic", "admin14", Role.ADMIN)
        );

        clientService.createClient(
                createUser("cli@gmail.com", "cli", "Client", "Clientic", "client", Role.CLIENT)
        );


    }

    private CreateUserRequest createUser(String email, String username, String firstName, String lastName, String password, Role role) {
        CreateUserRequest userRequestDto = new CreateUserRequest();
        userRequestDto.setEmail(email);
        userRequestDto.setUsername(username);
        userRequestDto.setFirstName(firstName);
        userRequestDto.setLastName(lastName);
        userRequestDto.setPassword(password);
        userRequestDto.setRole(role);
        return userRequestDto;
    }
}