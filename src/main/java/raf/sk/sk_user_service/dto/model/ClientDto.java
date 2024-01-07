package raf.sk.sk_user_service.dto.model;

import java.util.List;

public class ClientDto extends UserDto {

    private int scheduledTrainings;

    private List<MembershipCardDto> membershipCards;

    private ClientDto() {
    }

    public List<MembershipCardDto> getMembershipCards() {
        return membershipCards;
    }


    public int getScheduledTrainings() {
        return scheduledTrainings;
    }

    // Builder pattern
    public static class Builder extends UserDto.Builder {

        private List<MembershipCardDto> membershipCards;
        private int scheduledTrainings;


        public Builder() {

        }

        public Builder memberCards(List<MembershipCardDto> cards) {
            this.membershipCards = cards;
            return this;
        }

        public Builder scheduledTrainings(int scheduledTrainings) {
            this.scheduledTrainings = scheduledTrainings;
            return this;
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
            clientDto.membershipCards = this.membershipCards;
            clientDto.scheduledTrainings = this.scheduledTrainings;
            return clientDto;
        }
    }
}
