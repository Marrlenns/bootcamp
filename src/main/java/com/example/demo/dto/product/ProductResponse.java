package com.example.demo.dto.product;

import com.example.demo.enums.Type;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String name;
    private Type type;
    private Integer price;
    private String description;
    private String created_date;
}
