package com.example.lab3.controllers;

import com.example.lab3.model.Client;
import com.example.lab3.model.User;
import com.example.lab3.repos.ClientRepo;
import com.example.lab3.repos.UserRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Properties;

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;
    ClientRepo clientRepo;

    @GetMapping(value = "getAllUsers")
    public @ResponseBody Iterable<User> getAllUsers() {
//        List<User> users= userRepo.findAll();
        //create json from this list
        return userRepo.findAll();
    }

    @PostMapping(value = "createUser")
    public @ResponseBody User createNewUser(User user) {
        userRepo.save(user);
        return userRepo.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    //You should not do this
//    @GetMapping(value = "validateUser")
//    public @ResponseBody User validateUser(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password){
//        return userRepo.getUserByUsernameAndPassword(username, password);
//    }

    @PostMapping(value = "validateUser")
    public @ResponseBody User validateUser(@RequestBody String info) {

        /*
        {
            "username": "admin",
            "password": "admin"
        }
         */

        Gson gson = new Gson();
        Properties properties = gson.fromJson(info, Properties.class);
        return userRepo.getUserByUsernameAndPassword(properties.getProperty("username"), properties.getProperty("password"));
    }


    @DeleteMapping(value = "deleteUser/{id}")
    public @ResponseBody User deleteUserById(@PathVariable(name = "id") int id) {
        userRepo.deleteById(id);
        return userRepo.getReferenceById(id);
    }

    @PostMapping(value = "updateUser")
    public @ResponseBody User updateUser(User user) {
        userRepo.save(user);
        return userRepo.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
//    @GetMapping(value = "test")
//    public String test(){
//        return "Yo";
//    }
}
