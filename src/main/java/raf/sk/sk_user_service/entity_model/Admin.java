package raf.sk.sk_user_service.entity_model;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin extends User {

    public Admin() {
    }

}