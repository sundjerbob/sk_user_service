package raf.sk.sk_user_service.entity_model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "member_cards")
public class MemberCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "starting_date")
    private LocalDate startingDate;

    @Column(name = "duration_in_days")
    private int durationInDays;
}
