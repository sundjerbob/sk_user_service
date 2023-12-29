package raf.sk.sk_user_service.dto.model;


import raf.sk.sk_user_service.enumeration.Role;

public abstract class UserDto {

    protected String email;
    protected String firstName;
    protected String lastName;

    protected String username;

    protected String dateOfBirth;

    protected String role;

    protected boolean disabled;


    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public static abstract class Builder {

        protected String email;
        protected String firstName;
        protected String lastName;
        protected String username;
        protected String role;

        protected String dateOfBirth;

        protected boolean disabled;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }


        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder role(Object role) {

            if (role instanceof Role)
                this.role = ((Role) role).name();

            else if (role instanceof String)
                this.role = role.toString();

            else
                throw new RuntimeException("Role parameter not defined inside UserDto.Builder !");

            return this;
        }

        public Builder dateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder disabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

    }


}
