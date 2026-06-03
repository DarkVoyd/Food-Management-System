package com.example.courseworkitfu.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public final class Restaurant extends User{
    private String description;
    private String address;
    private LocalDateTime happyHours;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "restaurant_cuisines")
    @Enumerated(EnumType.STRING)
    @Column(name = "cuisine")
    private Set<Cuisine> cuisineTypes = new HashSet<>();
    private double rating;
    private boolean isOpen;
    private LocalTime openingTime;
    private LocalTime closingTime;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dish> menu;

    public Restaurant(String username, String password, String phoneNum, String description, String address, LocalDateTime happyHours) {
        super(username, password, phoneNum);
        this.description = description;
        this.address = address;
        this.happyHours = happyHours;
        this.isOpen = true;
    }

    public Restaurant(String username, String password, String phoneNum, String description, String address, LocalDateTime happyHours, Set<Cuisine> cuisineTypes, LocalTime openingTime, LocalTime closingTime) {
        super(username, password, phoneNum);
        this.description = description;
        this.address = address;
        this.happyHours = happyHours;
        this.cuisineTypes = cuisineTypes != null ? new HashSet<>(cuisineTypes) : new HashSet<>();
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.isOpen = true;
    }
}
