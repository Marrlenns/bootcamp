package com.example.demo.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private Integer age;
    private String name;
    private Integer course;
}
