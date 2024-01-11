package com.example.demo.mapper;

import com.example.demo.dto.user.UserResponse;
import com.example.demo.entities.User;

public interface UserMapper {

    UserResponse toDto(User user);
}
