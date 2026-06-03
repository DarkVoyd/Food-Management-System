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
//@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
//    @Column(length = 30) if you wish to control varchar length
    @Column(unique = true)
    protected String username;
    protected String password;
    protected String email;
    protected LocalDateTime dateCreated;
    protected boolean isAdmin;
    protected boolean isActive;
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


   //Overloading - same method name, but different parameters
   public String renderUserData() {
        return "Current user: " + username;
    }
    public String renderUserData(String message){
        return message + " " + username;
    }

 //Override - same method name as parent class, but with different implementation
 @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateCreated=" + dateCreated +
                ", isAdmin=" + isAdmin +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}
