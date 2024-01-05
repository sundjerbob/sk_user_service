package raf.sk.sk_user_service.entity_model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "member_cards")
public class MemberCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "starting_date")
    private Date startingDate;

    @Column(name = "duration_in_days")
    private int durationInDays;

    public MemberCard setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
        return this;
    }

    public MemberCard setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
        return this;
    }
}
