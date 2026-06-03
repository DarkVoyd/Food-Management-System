package com.example.lab3.controllers;

import com.example.lab3.model.*;
import com.example.lab3.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired private UserRepository userRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private DriverRepository driverRepository;
    @Autowired private RestaurantRepository restaurantRepository;
    @Autowired private FoodOrderRepository orderRepository;
    @Autowired private MessageRepository messageRepository;
    @Autowired private DishRepository dishRepository;

    @GetMapping
    public List<User> getAllUsers(@RequestParam(required = false) String type) {
        Iterable<User> users = userRepository.findAll();
        if (type == null || type.isEmpty() || "All Types".equalsIgnoreCase(type)) {
            return StreamSupport.stream(users.spliterator(), false).collect(Collectors.toList());
        }
        return StreamSupport.stream(users.spliterator(), false)
                .filter(u -> type.equalsIgnoreCase(u.getUserType()))
                .collect(Collectors.toList());
    }

    @PostMapping("/customer")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
        try {
            if (customer.getId() != null && customer.getId() != 0) {
                Customer existing = customerRepository.findById(customer.getId()).orElse(null);
                if (existing != null) {
                    existing.setUsername(customer.getUsername());
                    existing.setPassword(customer.getPassword());
                    existing.setEmail(customer.getEmail());
                    existing.setPhoneNum(customer.getPhoneNum());
                    existing.setName(customer.getName());
                    existing.setSurname(customer.getSurname());
                    existing.setAddress(customer.getAddress());
                    existing.setCardNo(customer.getCardNo());
                    return ResponseEntity.ok(customerRepository.save(existing));
                }
            }
            customer.setDateCreated(LocalDateTime.now());
            customer.setActive(true);
            return ResponseEntity.ok(customerRepository.save(customer));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/driver")
    public ResponseEntity<?> saveDriver(@RequestBody Driver driver) {
        try {
            if (driver.getId() != null && driver.getId() != 0) {
                Driver existing = driverRepository.findById(driver.getId()).orElse(null);
                if (existing != null) {
                    existing.setUsername(driver.getUsername());
                    existing.setPassword(driver.getPassword());
                    existing.setEmail(driver.getEmail());
                    existing.setPhoneNum(driver.getPhoneNum());
                    existing.setName(driver.getName());
                    existing.setSurname(driver.getSurname());
                    existing.setDriverLicence(driver.getDriverLicence());
                    return ResponseEntity.ok(driverRepository.save(existing));
                }
            }
            driver.setDateCreated(LocalDateTime.now());
            driver.setActive(true);
            return ResponseEntity.ok(driverRepository.save(driver));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/restaurant")
    public ResponseEntity<?> saveRestaurant(@RequestBody Restaurant restaurant) {
        try {
            if (restaurant.getId() != null && restaurant.getId() != 0) {
                Restaurant existing = restaurantRepository.findById(restaurant.getId()).orElse(null);
                if (existing != null) {
                    existing.setUsername(restaurant.getUsername());
                    existing.setPassword(restaurant.getPassword());
                    existing.setEmail(restaurant.getEmail());
                    existing.setPhoneNum(restaurant.getPhoneNum());
                    existing.setAddress(restaurant.getAddress());
                    existing.setDescription(restaurant.getDescription());
                    return ResponseEntity.ok(restaurantRepository.save(existing));
                }
            }
            restaurant.setDateCreated(LocalDateTime.now());
            restaurant.setActive(true);
            return ResponseEntity.ok(restaurantRepository.save(restaurant));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        try {
            User u = userRepository.findById(id).orElse(null);
            if (u != null) {
                List<Message> userMessages = messageRepository.findAll().stream()
                    .filter(m -> (m.getSender() != null && m.getSender().getId() == id) || (m.getReceiver() != null && m.getReceiver().getId() == id))
                    .collect(Collectors.toList());
                messageRepository.deleteAll(userMessages);

                List<FoodOrder> userOrders = orderRepository.findAll().stream()
                    .filter(o -> (o.getBuyer() != null && o.getBuyer().getId() == id) ||
                                 (o.getDeliveryPerson() != null && o.getDeliveryPerson().getId() == id) ||
                                 (o.getRestaurant() != null && o.getRestaurant().getId() == id))
                    .collect(Collectors.toList());
                orderRepository.deleteAll(userOrders);

                if (u instanceof Restaurant) {
                    List<Dish> dishes = dishRepository.findAll().stream()
                        .filter(d -> d.getRestaurant() != null && d.getRestaurant().getId() == id)
                        .collect(Collectors.toList());
                    dishRepository.deleteAll(dishes);
                }

                userRepository.deleteById(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
