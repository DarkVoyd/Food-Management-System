package com.example.lab3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@DiscriminatorValue("Driver")
public class Driver extends User {
    private String name;
    private String surname;
    private String driverLicence;
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
    private LocalDate birthDate;
    private Boolean isAvailable;
    private Double rating;
    private Integer totalDeliveries;
    private String vehiclePlateNumber;

    public Driver(String username, String password, String phoneNum, String driverLicence, VehicleType vehicleType, LocalDate birthDate) {
        super(username, password, phoneNum);
        this.driverLicence = driverLicence;
        this.vehicleType = vehicleType;
        this.birthDate = birthDate;
        this.isAvailable = true;
    }

    public Driver(String username, String password, String phoneNum, String driverLicence, VehicleType vehicleType, LocalDate birthDate, String vehiclePlateNumber) {
        super(username, password, phoneNum);
        this.driverLicence = driverLicence;
        this.vehicleType = vehicleType;
        this.birthDate = birthDate;
        this.vehiclePlateNumber = vehiclePlateNumber;
        this.isAvailable = true;
    }
}
