package com.example.demo.controller;

import com.example.demo.dto.product.*;
import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    @PostMapping("/product/register")
    public String register(@RequestBody ProductRequest productRequest){
        productService.register(productRequest);
        return "Product: " + productRequest.getName() + " - added successfully!";
    }

    @GetMapping("/product/all")
    public List<ProductResponse> products(){return productService.findAll();}

    @GetMapping("/product/{id}")
    public ProductDetailResponse productResponse(@PathVariable Long id){
        return productService.getById(id);
    }

    @DeleteMapping("/product/delete/{id}")
    public String delete(@PathVariable Long id){
        productService.deleteById(id);
        return "Product with id: " + id + " - deleted successfully!";
    }

    @PutMapping("/product/fullupdate/{id}")
    public ProductDetailResponse productUpdate(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        productService.updateById(id, productRequest);
        return productService.getById(id);
    }
}
