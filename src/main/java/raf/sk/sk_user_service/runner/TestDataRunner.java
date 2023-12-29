package raf.sk.sk_user_service.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import raf.sk.sk_user_service.dto.request.CreateUserRequest;
import raf.sk.sk_user_service.enumeration.Role;
import raf.sk.sk_user_service.service.api.AdminServiceApi;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private final AdminServiceApi adminService;

    public TestDataRunner(AdminServiceApi adminService) {
        this.adminService = adminService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Add initial users using UserService
        addAdmin("admin@gmail.com", "admin", "Admin", "Admin", "admin");
        addAdmin("client@gmail.com", "client", "Client", "Client", "client");
    }

    private void addAdmin(String email, String username, String firstName, String lastName, String password) {
        CreateUserRequest userRequestDto = new CreateUserRequest();
        userRequestDto.setEmail(email);
        userRequestDto.setUsername(username);
        userRequestDto.setFirstName(firstName);
        userRequestDto.setLastName(lastName);
        userRequestDto.setPassword(password);
        userRequestDto.setRole(Role.ADMIN);

        adminService.createAdmin(userRequestDto);
    }
}