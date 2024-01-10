package com.example.demo.controller;

import com.example.demo.dto.user.UserRequest;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {

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
    @PostMapping("/user/register")
    public String register(@RequestBody UserRequest userRequest){
        userService.register(userRequest);
        return userRequest.getName() + " registered successfully!";
    }
    //response
    @GetMapping("/user/{id}")
    public UserResponse userResponse(@PathVariable Long id){
        return userService.getById(id);
    }

    @GetMapping("/user/all")
    public List<User> users(){
        return userRepository.findAll();
    }

    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        userService.deleteById(id);
        return "User with id: " + id + " successfully deleted";
    }

    @PutMapping("/user/fullupdate/{id}")
    public UserResponse userUpdate(@PathVariable Long id, @RequestBody UserRequest userRequest){
        userService.updateById(id, userRequest);
        return userService.getById(id);
    }

}
