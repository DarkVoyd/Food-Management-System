package com.example.lab3.repos;

import com.example.lab3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User getUserByUsernameAndPassword(String username, String password);
}
