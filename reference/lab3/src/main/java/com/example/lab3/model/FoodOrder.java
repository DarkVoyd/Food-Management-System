package com.example.lab3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime createdAt;
    private LocalDateTime deliveredAt;
    @ManyToOne
    private Client buyer;
    @ManyToOne
    private Driver deliveryPerson;
    @ManyToOne
    private Restaurant restaurant;
    private Cuisine cuisine;
    private OrderStatus status;
    private double totalPrice;
    private double deliveryFee;
    private String paymentMethod;
    private int estimatedDeliveryMin;
    private String specialInstructions;
    @Transient
    private Review review;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "orders", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Dish> items;
    @Transient
    private List<Message> messages;

    public FoodOrder(Client buyer, Restaurant restaurant, double totalPrice, double deliveryFee, String paymentMethod, String specialInstructions, List<Dish> items) {
        this.buyer = buyer;
        this.restaurant = restaurant;
        this.totalPrice = totalPrice;
        this.deliveryFee = deliveryFee;
        this.paymentMethod = paymentMethod;
        this.specialInstructions = specialInstructions;
        this.items = items;
    }
}
