package com.example.lab3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
@jakarta.persistence.DiscriminatorValue("REVIEW")
public class Review extends Message {
    private Integer rating = 5;
    private String title;
    private LocalDateTime reviewDate;
    @ManyToOne
    private Restaurant reviewedRestaurant;
    @ManyToOne
    private Driver reviewedDriver;
}
