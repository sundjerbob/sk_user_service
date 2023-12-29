package raf.sk.sk_user_service.entity_model;

import jakarta.persistence.*;
import raf.sk.sk_user_service.enumeration.Role;

import java.time.LocalDate;

@Entity
@Table(name = "admins")
public class Admin extends User {

    public Admin() {
    }

}