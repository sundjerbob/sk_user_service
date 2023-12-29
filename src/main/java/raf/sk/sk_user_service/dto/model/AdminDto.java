package raf.sk.sk_user_service.dto.model;

public class AdminDto extends UserDto {


    private AdminDto() {

    }

    public static class Builder extends UserDto.Builder {


        public Builder() {
        }

        public AdminDto build() {
            AdminDto adminDto = new AdminDto();
            adminDto.email = email;
            adminDto.username = username;
            adminDto.firstName = firstName;
            adminDto.lastName = lastName;
            adminDto.dateOfBirth = dateOfBirth;
            adminDto.role = role;
            adminDto.disabled = disabled;
            return adminDto;
        }
    }


}
