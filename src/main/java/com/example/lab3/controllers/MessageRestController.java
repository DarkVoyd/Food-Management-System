package com.example.lab3.controllers;

import com.example.lab3.model.*;
import com.example.lab3.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {

    @Autowired private MessageRepository messageRepository;
    @Autowired private ReviewRepository reviewRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private FoodOrderRepository orderRepository;
    @Autowired private RestaurantRepository restaurantRepository;
    @Autowired private DriverRepository driverRepository;

    @GetMapping
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @PostMapping("/message")
    public ResponseEntity<?> saveMessage(@RequestBody Message message) {
        try {
            // Phase 1: ID Nullification
            if (message.getId() != null && message.getId() == 0) {
                message.setId(null);
            }

            // Phase 2: Transient Relationship Scrubbing
            message.setSender(scrubUser(message.getSender()));
            message.setReceiver(scrubUser(message.getReceiver()));
            message.setRelatedOrder(scrubOrder(message.getRelatedOrder()));
            
            if (message.getSentAt() == null) message.setSentAt(LocalDateTime.now());
            return ResponseEntity.ok(messageRepository.save(message));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/review")
    public ResponseEntity<?> saveReview(@RequestBody Review review) {
        try {
            // Phase 1: ID Nullification
            if (review.getId() != null && review.getId() == 0) {
                review.setId(null);
            }

            // Phase 2: Transient Relationship Scrubbing
            review.setSender(scrubUser(review.getSender()));
            review.setReceiver(scrubUser(review.getReceiver()));
            review.setRelatedOrder(scrubOrder(review.getRelatedOrder()));
            review.setReviewedRestaurant(scrubRestaurant(review.getReviewedRestaurant()));
            review.setReviewedDriver(scrubDriver(review.getReviewedDriver()));
            
            if (review.getSentAt() == null) review.setSentAt(LocalDateTime.now());
            if (review.getReviewDate() == null) review.setReviewDate(LocalDateTime.now());
            return ResponseEntity.ok(reviewRepository.save(review));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    private User scrubUser(User u) {
        if (u == null || u.getId() == null || u.getId() == 0) return null;
        return userRepository.findById(u.getId()).orElse(null);
    }

    private FoodOrder scrubOrder(FoodOrder o) {
        if (o == null || o.getId() == null || o.getId() == 0) return null;
        return orderRepository.findById(o.getId()).orElse(null);
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
    public ResponseEntity<?> deleteMessage(@PathVariable int id) {
        try {
            messageRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
