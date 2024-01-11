package com.example.demo.entities;

import com.example.demo.enums.Type;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "products_table")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String description;
    private int price;
    private String created_date;

    @ManyToOne
    User owner;
}
