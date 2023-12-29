package raf.sk.sk_user_service.entity_model;

import jakarta.persistence.*;
import raf.sk.sk_user_service.enumeration.Role;

import java.time.LocalDate;


@Entity
@Table(name = "gym_managers")
public class GymManager extends User {

    public GymManager() {

    }

    @Column(name = "gym_name")
    private String gymName;

    @Column(name = "date_of_employment")
    private LocalDate dateOfEmployment;

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public LocalDate getDateOfEmployment() {
        return dateOfEmployment;
    }

    public void setDateOfEmployment(LocalDate dateOfEmployment) {
        this.dateOfEmployment = dateOfEmployment;
    }
}

