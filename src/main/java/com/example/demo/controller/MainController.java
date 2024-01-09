package com.example.demo.controller;

import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.entities.User;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class MainController {

    private UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/hello")
    public String hello(@RequestParam String name){
        ArrayList<String>names = new ArrayList<>();
        names.add("adyl");
        names.add("bektur");
        names.add("erdan");
        names.add("sanzhar");
        if(names.contains(name.toLowerCase())){
            return "Salam " + name + " Chushpan!!";
        }
        return "Salam " + name + " Gospodin!!";
    }
    // request
    @PostMapping("/register")
    public String register(@RequestBody UserRequest userRequest){
        userService.register(userRequest);
        return userRequest.getName() + " registered successfully!";
    }
    //response
    @GetMapping("/user/{id}")
    public UserResponse userResponse(@PathVariable Long id){
        return userService.getById(id);
    }

    @GetMapping("/allusers")
    public List<User> users(){
        return userRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteById(id);
        return "User with id: " + id + " successfully deleted";
    }

    @PutMapping("/fullupdate/{id}")
    public UserResponse userUpdate(@PathVariable Long id, @RequestBody UserRequest userRequest){
        userService.updateById(id, userRequest);
        return userService.getById(id);
    }

}
