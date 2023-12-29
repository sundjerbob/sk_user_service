package raf.sk.sk_user_service.dto.model;

import java.util.Date;

public class GymManagerDto extends UserDto {

    private String dateOfEmployment;

    private String gymName;


    public static class Builder extends UserDto.Builder {
        private String dateOfEmployment;

        private String gymName;


        public Builder setDateOfEmployment(String dateOfEmployment) {
            this.dateOfEmployment = dateOfEmployment;
            return this;
        }

        public Builder gymName(String gymName) {
            this.gymName = gymName;
            return this;
        }

        public GymManagerDto build() {
            GymManagerDto gymManager = new GymManagerDto();
            gymManager.email = email;
            gymManager.username = username;
            gymManager.firstName = firstName;
            gymManager.lastName = lastName;
            gymManager.dateOfBirth = dateOfBirth;
            gymManager.role = role;
            gymManager.disabled = disabled;

            gymManager.dateOfEmployment = dateOfEmployment;
            gymManager.gymName = gymName;
            return gymManager;
        }
    }
}
