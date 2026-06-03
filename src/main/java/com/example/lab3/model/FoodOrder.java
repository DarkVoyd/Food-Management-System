package com.example.lab3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FoodOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime createdAt;
    private LocalDateTime deliveredAt;
    @ManyToOne
    private Customer buyer;
    @ManyToOne
    private Driver deliveryPerson;
    @ManyToOne
    private Restaurant restaurant;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Double totalPrice = 0.0;
    private Double deliveryFee = 0.0;
    private String paymentMethod;
    private Integer estimatedDeliveryMin = 0;
    private String specialInstructions;
    @ManyToMany
    private List<Dish> items;
    @OneToMany(mappedBy = "relatedOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private List<Message> messages;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (status == null) status = OrderStatus.PENDING;
        if (totalPrice == null) totalPrice = 0.0;
        if (deliveryFee == null) deliveryFee = 0.0;
        if (estimatedDeliveryMin == null) estimatedDeliveryMin = 0;
    }

    @Override
    public String toString() {
        return "Order #" + id;
    }
}
