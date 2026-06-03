package com.example.lab3.repos;

import com.example.lab3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    User getUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
