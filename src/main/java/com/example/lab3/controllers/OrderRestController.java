package com.example.lab3.controllers;

import com.example.lab3.model.*;
import com.example.lab3.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

    @Autowired private FoodOrderRepository orderRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private RestaurantRepository restaurantRepository;
    @Autowired private DriverRepository driverRepository;

    @GetMapping
    public List<FoodOrder> getAllOrders(@RequestParam(required = false) OrderStatus status) {
        List<FoodOrder> all = orderRepository.findAll();
        if (status == null) return all;
        return all.stream().filter(o -> o.getStatus() == status).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody FoodOrder order) {
        try {
            // Phase 1: ID Nullification
            if (order.getId() != null && order.getId() == 0) {
                order.setId(null);
            }

            // Phase 2: Transient Relationship Scrubbing
            order.setBuyer(scrubCustomer(order.getBuyer()));
            order.setRestaurant(scrubRestaurant(order.getRestaurant()));
            order.setDeliveryPerson(scrubDriver(order.getDeliveryPerson()));

            if (order.getId() != null) {
                FoodOrder existing = orderRepository.findById(order.getId()).orElse(null);
                if (existing != null) {
                    existing.setBuyer(order.getBuyer());
                    existing.setRestaurant(order.getRestaurant());
                    existing.setDeliveryPerson(order.getDeliveryPerson());
                    existing.setStatus(order.getStatus());
                    existing.setTotalPrice(order.getTotalPrice());
                    existing.setSpecialInstructions(order.getSpecialInstructions());
                    return ResponseEntity.ok(orderRepository.save(existing));
                }
            }
            
            if (order.getCreatedAt() == null) order.setCreatedAt(LocalDateTime.now());
            if (order.getStatus() == null) order.setStatus(OrderStatus.PENDING);
            return ResponseEntity.ok(orderRepository.save(order));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    private Customer scrubCustomer(Customer c) {
        if (c == null || c.getId() == null || c.getId() == 0) return null;
        return customerRepository.findById(c.getId()).orElse(null);
    }

    private Restaurant scrubRestaurant(Restaurant r) {
        if (r == null || r.getId() == null || r.getId() == 0) return null;
        return restaurantRepository.findById(r.getId()).orElse(null);
    }

    private Driver scrubDriver(Driver d) {
        if (d == null || d.getId() == null || d.getId() == 0) return null;
        return driverRepository.findById(d.getId()).orElse(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable int id) {
        try {
            orderRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
