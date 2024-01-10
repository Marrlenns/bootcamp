package com.example.demo.services;

import com.example.demo.dto.product.ProductResponse;
import com.example.demo.dto.product.ProductRequest;

public interface ProductService {
    void register(ProductRequest productRequest);

    ProductResponse getById(Long id);

    void deleteById(Long id);

    void updateById(Long id, ProductRequest productRequest);

}
