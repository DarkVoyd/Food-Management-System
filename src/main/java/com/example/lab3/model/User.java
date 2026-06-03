package com.example.lab3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column(unique = true)
    protected String username;
    protected String password;
    protected String email;
    protected LocalDateTime dateCreated;
    protected boolean isAdmin = false;
    protected boolean isActive = true;
    protected String phoneNum;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.isActive = true;
    }

    public User(String username, String password, String phoneNum) {
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.isActive = true;
    }

    public User(String username, String password, String phoneNum, String email) {
        this.username = username;
        this.password = password;
        this.phoneNum = phoneNum;
        this.email = email;
        this.isActive = true;
    }

    @Override
    public String toString() {
        return username;
    }

    @Transient
    @com.fasterxml.jackson.annotation.JsonProperty("userType")
    public String getUserType() {
        String name = this.getClass().getSimpleName();
        if (name.contains("$HibernateProxy$")) {
            name = name.substring(0, name.indexOf("$"));
        }
        return name;
    }
}
