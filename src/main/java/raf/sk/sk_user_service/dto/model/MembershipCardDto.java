package raf.sk.sk_user_service.dto.model;

import java.time.LocalDate;

public class MembershipCardDto {
    private LocalDate startingDate;

    private int durationInDays;

    private String gymName;

    public MembershipCardDto() {
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public String getGymName() {
        return gymName;
    }
}
