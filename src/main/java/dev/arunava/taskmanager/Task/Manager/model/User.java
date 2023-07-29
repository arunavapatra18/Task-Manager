package dev.arunava.taskmanager.Task.Manager.model;

import jakarta.persistence.*;
import java.util.UUID;

/**
 * The User entity class.
 *
 * It is used as the schema for User in the db.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String fullName;
    private String email;
    private String password;
    private String roles;

    public User(){

    }

    public User(String fullName, String username, String email, String password, String roles) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

