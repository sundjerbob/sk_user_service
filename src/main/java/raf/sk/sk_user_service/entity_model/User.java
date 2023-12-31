package raf.sk.sk_user_service.entity_model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users", indexes = {@Index(columnList = "username", unique = true), @Index(columnList = "email")})

@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    protected String password;

    @Column(nullable = false)
    protected String email;

    @Column(name = "date_of_birth")
    protected LocalDate dateOfBirth;

    @Column(name = "first_name")
    protected String firstName;

    @Column(name = "last_name")
    protected String lastName;

    @Column(name = "role", nullable = false)
    protected Role role;

    @Column(name = "disabled", nullable = false)
    protected boolean disabled;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
