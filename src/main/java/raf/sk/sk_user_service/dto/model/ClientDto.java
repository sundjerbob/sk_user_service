package raf.sk.sk_user_service.dto.model;

public class ClientDto extends UserDto {

    private long memberCardNumber;
    private int scheduledTrainings;

    private ClientDto() {
    }

    public long getMemberCardNumber() {
        return memberCardNumber;
    }


    public int getScheduledTrainings() {
        return scheduledTrainings;
    }

    // Builder pattern
    public static class Builder extends UserDto.Builder {
        private long memberCardNumber;
        private int scheduledTrainings;


        public Builder() {

        }

        public Builder memberCardNumber(long memberCardNumber) {
            this.memberCardNumber = memberCardNumber;
            return this;
        }

        public Builder scheduledTrainings(int scheduledTrainings) {
            this.scheduledTrainings = scheduledTrainings;
            return this;
        }

        public void setMemberCard(long memberCard) {
            this.memberCardNumber = memberCard;
        }

        public ClientDto build() {
            ClientDto clientDto = new ClientDto();
            clientDto.email = email;
            clientDto.username = username;
            clientDto.firstName = firstName;
            clientDto.lastName = lastName;
            clientDto.dateOfBirth = dateOfBirth;
            clientDto.role = role;
            clientDto.disabled = disabled;

            clientDto.memberCardNumber = this.memberCardNumber;
            clientDto.scheduledTrainings = this.scheduledTrainings;

            return clientDto;
        }
    }
}
