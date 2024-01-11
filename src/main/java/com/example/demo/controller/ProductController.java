package com.example.demo.controller;

import com.example.demo.dto.product.*;
import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/product")
@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    @PostMapping("/register")
    public String register(@RequestBody ProductRequest productRequest){
        productService.register(productRequest);
        return "Product: " + productRequest.getName() + " - added successfully!";
    }

    @GetMapping("/all")
    public List<ProductResponse> products(){return productService.findAll();}

    @GetMapping("/{id}")
    public ProductDetailResponse productResponse(@PathVariable Long id){
        return productService.getById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        productService.deleteById(id);
        return "Product with id: " + id + " - deleted successfully!";
    }

    @PutMapping("/fullupdate/{id}")
    public ProductDetailResponse productUpdate(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        productService.updateById(id, productRequest);
        return productService.getById(id);
    }

    @GetMapping("/user/{userId}")
    public List<ProductResponse> userProducts(@PathVariable Long userId){
        return productService.getUserProducts(userId);
    }

}
