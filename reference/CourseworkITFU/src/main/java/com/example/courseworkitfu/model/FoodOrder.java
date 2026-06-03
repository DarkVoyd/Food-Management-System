package com.example.courseworkitfu.model;

import java.time.LocalDateTime;
import java.util.List;

public class FoodOrder {
    private int id;
    private LocalDateTime createdAt;
    private LocalDateTime deliveredAt;
    private Client buyer;
    private Driver deliveryPerson;
    private Restaurant restaurant;
    private Cuisine cuisine;
    private OrderStatus status;
    private double totalPrice;
    private double deliveryFee;
    private String paymentMethod;
    private int estimatedDeliveryMin;
    private String specialInstructions;
    private Review review;

    private List<Dish> items;
    private List<Message> messages;

}
