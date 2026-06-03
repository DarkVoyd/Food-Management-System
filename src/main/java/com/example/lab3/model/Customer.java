package com.example.lab3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue("Customer")
public class Customer extends User {
    private String name;
    private String surname;
    private String cardNo;
    private int bonusPoints;
    private String address;
    private LocalDate dateOfBirth;
    private double balance;

    public Customer(String username, String password, String phoneNum, String cardNo) {
        super(username, password, phoneNum);
        this.cardNo = cardNo;
    }

    public Customer(String username, String password, String phoneNum, String cardNo, String address, LocalDate dateOfBirth) {
        super(username, password, phoneNum);
        this.cardNo = cardNo;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }
}
