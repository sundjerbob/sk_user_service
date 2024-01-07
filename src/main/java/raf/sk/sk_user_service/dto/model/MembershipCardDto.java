package raf.sk.sk_user_service.dto.model;

import java.util.Date;

public class MembershipCardDto {
    private Date startingDate;

    private int durationInDays;

    public MembershipCardDto() {
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }
}
