package com.example.lab3.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client extends User{
    private String name;
    private String surname;
    private String cardNo;
    private int bonusPoints;
    private String address;
    private LocalDate dateOfBirth;
    private double balance;
    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<FoodOrder> myOrders;

    public Client(String username, String password, String phoneNum, String cardNo) {
        super(username, password, phoneNum);
        this.cardNo = cardNo;
    }

    public Client(String username, String password, String phoneNum, String cardNo, String address, LocalDate dateOfBirth) {
        super(username, password, phoneNum);
        this.cardNo = cardNo;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String renderUserData() {
        return "Sveikas kliente: " + name + " " + surname;
    }

}
