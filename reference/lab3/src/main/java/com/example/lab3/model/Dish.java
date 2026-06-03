package com.example.lab3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private float price;
    @Enumerated
    private DishCategory category;
    private int preparationTimeMin;
    private boolean isAvailable;
    private String imageUrl;
    private float weight;
    @Transient
    private List<String> allergens;
    private int calories;
    @ManyToOne
    private Restaurant restaurant;
    @ManyToMany
    private List<FoodOrder> orders;

}
