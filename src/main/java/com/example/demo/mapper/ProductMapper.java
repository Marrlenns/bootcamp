package com.example.demo.mapper;

import com.example.demo.dto.product.ProductDetailResponse;
import com.example.demo.dto.product.ProductResponse;
import com.example.demo.entities.Product;

import java.util.List;

public interface ProductMapper {
    ProductDetailResponse toDetailDto(Product product);

    ProductResponse toDto(Product product);

    List<ProductResponse> toDtos(List<Product> products);
}
