package com.example.demo.dto.product;

import com.example.demo.enums.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private String type;
    private String description;
    private Integer price;
    private String created_date;
}
