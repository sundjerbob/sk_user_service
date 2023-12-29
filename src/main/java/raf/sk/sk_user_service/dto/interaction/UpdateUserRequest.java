package raf.sk.sk_user_service.dto.interaction;


import java.time.LocalDate;

public class UpdateUserRequest {


    private String email;

    private String username;

    private String firstName;

    private String lastName;
    private LocalDate dateOfBirth;


    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }


}
