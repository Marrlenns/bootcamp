package com.example.demo.mapper.impl;

import com.example.demo.dto.product.*;
import com.example.demo.entities.Product;
import com.example.demo.mapper.ProductMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDetailResponse toDetailDto(Product product) {
        ProductDetailResponse productDetailResponse = new ProductDetailResponse();
        productDetailResponse.setId(product.getId());
        productDetailResponse.setName(product.getName());
        productDetailResponse.setType(product.getType());
        productDetailResponse.setPrice(product.getPrice());
        productDetailResponse.setDescription(product.getDescription());
        productDetailResponse.setCreated_date(product.getCreated_date());
        if(product.getOwner() == null)
            productDetailResponse.setOwner("No owner");
        else
            productDetailResponse.setOwner(product.getOwner().getName());
        return productDetailResponse;
    }

    @Override
    public ProductResponse toDto(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        return productResponse;
    }

    public List<ProductResponse> toDtos(List<Product> products){
        List<ProductResponse> productResponseList = new ArrayList<>();
        for(Product product: products){
            productResponseList.add(toDto(product));
        }
        return productResponseList;
    }
}
