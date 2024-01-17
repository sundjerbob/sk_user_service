package raf.sk.sk_user_service.entity_model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "membership_cards")
public class MembershipCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "starting_date")
    private LocalDate startingDate;

    @Column(name = "duration_in_days")
    private int durationInDays;

    @Column(name = "gym_name")
    private String gymName;

    @Column(name = "bookedWorkouts")
    private Integer bookedWorkouts;


    public boolean isActive() {
        LocalDate expireDate = startingDate.plusDays(durationInDays);
        LocalDate currentDate = LocalDate.now();
        return currentDate.isBefore(expireDate);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MembershipCard setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
        return this;
    }

    public MembershipCard setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
        return this;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public MembershipCard setGymName(String gymName) {
        this.gymName = gymName;
        return this;
    }


    public String getGymName() {
        return gymName;
    }

    public Integer getBookedWorkouts() {
        return bookedWorkouts;
    }

    public void setBookedWorkouts(Integer bookedWorkouts) {
        this.bookedWorkouts = bookedWorkouts;
    }
}
