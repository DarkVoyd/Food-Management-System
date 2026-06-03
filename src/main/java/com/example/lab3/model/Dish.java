package com.example.lab3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private Float price = 0.0f;
    @Enumerated(EnumType.STRING)
    private DishCategory category;
    private Integer preparationTimeMin = 0;
    private Boolean isAvailable = true;
    private Float weight = 0.0f;
    private Integer calories = 0;
    @ManyToOne
    private Restaurant restaurant;

    @PrePersist
    public void prePersist() {
        if (price == null) price = 0.0f;
        if (preparationTimeMin == null) preparationTimeMin = 0;
        if (isAvailable == null) isAvailable = true;
        if (weight == null) weight = 0.0f;
        if (calories == null) calories = 0;
    }

    @Override
    public String toString() {
        return title;
    }
}
