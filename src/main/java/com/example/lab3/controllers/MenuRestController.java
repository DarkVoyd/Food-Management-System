package com.example.lab3.controllers;

import com.example.lab3.model.Dish;
import com.example.lab3.repos.DishRepository;
import com.example.lab3.repos.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuRestController {

    @Autowired private DishRepository dishRepository;
    @Autowired private RestaurantRepository restaurantRepository;

    @GetMapping
    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> saveDish(@RequestBody Dish dish) {
        try {
            // Phase 1: ID Nullification (New Dish if ID=0)
            if (dish.getId() != null && dish.getId() == 0) {
                dish.setId(null);
            }

            // Phase 2: Relationship Scrubbing & Relinking
            if (dish.getRestaurant() != null && dish.getRestaurant().getId() != null && dish.getRestaurant().getId() != 0) {
                dish.setRestaurant(restaurantRepository.findById(dish.getRestaurant().getId()).orElse(null));
            } else {
                dish.setRestaurant(null);
            }

            // Normal update logic if ID exists (and not 0)
            if (dish.getId() != null) {
                Dish existing = dishRepository.findById(dish.getId()).orElse(null);
                if (existing != null) {
                    existing.setTitle(dish.getTitle());
                    existing.setDescription(dish.getDescription());
                    existing.setPrice(dish.getPrice() != null ? dish.getPrice() : existing.getPrice());
                    existing.setCategory(dish.getCategory());
                    existing.setPreparationTimeMin(dish.getPreparationTimeMin() != null ? dish.getPreparationTimeMin() : existing.getPreparationTimeMin());
                    existing.setCalories(dish.getCalories() != null ? dish.getCalories() : existing.getCalories());
                    existing.setIsAvailable(dish.getIsAvailable() != null ? dish.getIsAvailable() : existing.getIsAvailable());
                    existing.setWeight(dish.getWeight() != null ? dish.getWeight() : existing.getWeight());
                    
                    // Critical: Preserve restaurant if not changed in payload
                    if (dish.getRestaurant() != null) {
                        existing.setRestaurant(dish.getRestaurant());
                    }
                    
                    return ResponseEntity.ok(dishRepository.save(existing));
                }
            }

            // Final fallback defaults for new creations (handled by PrePersist as well but safe to set here)
            if (dish.getPrice() == null) dish.setPrice(0.0f);
            
            return ResponseEntity.ok(dishRepository.save(dish));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDish(@PathVariable int id) {
        try {
            dishRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
