package com.example.lab3.model;

import java.time.LocalDateTime;

public class Review extends Message{
    private int rating;
    private String title;
    private LocalDateTime reviewDate;
    private Restaurant reviewedRestaurant;
    private Driver reviewedDriver;
}
