package com.example.demo.services;

import com.example.demo.dto.user.UserRequest;
import com.example.demo.dto.user.UserResponse;

public interface UserService {
    UserResponse getById(Long id);

    void register(UserRequest userRequest);

    void deleteById(Long id);

    void updateById(Long id, UserRequest userRequest);
}
