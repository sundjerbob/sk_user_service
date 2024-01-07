package raf.sk.sk_user_service.entity_model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "membership_cards")
public class MembershipCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "starting_date")
    private Date startingDate;

    @Column(name = "duration_in_days")
    private int durationInDays;

    public MembershipCard setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
        return this;
    }

    public MembershipCard setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
        return this;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public int getDurationInDays() {
        return durationInDays;
    }
}
