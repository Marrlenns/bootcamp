package com.example.demo.services;

import com.example.demo.dto.product.ProductDetailResponse;
import com.example.demo.dto.product.ProductRequest;
import com.example.demo.dto.product.ProductResponse;
import com.example.demo.entities.Product;

import java.util.List;

public interface ProductService {
    void register(ProductRequest productRequest);

    ProductDetailResponse getById(Long id);

    void deleteById(Long id);

    void updateById(Long id, ProductRequest productRequest);

    List<ProductResponse> findAll();
}
