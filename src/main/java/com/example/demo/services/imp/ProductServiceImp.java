package com.example.demo.services.imp;

import com.example.demo.dto.product.*;
import com.example.demo.entities.Product;
import com.example.demo.enums.Type;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    public boolean checkType(String s){
        for (Type type: Type.values())
            if (type.name().equalsIgnoreCase(s))
                return true;

        return false;
    }

    @Override
    public void register(ProductRequest productRequest){
        Product product = new Product();
        product.setName(productRequest.getName());
        System.out.println(productRequest.getType());
        if(!checkType(productRequest.getType()))
            throw new BadRequestException("This type doesn't exist!");
        product.setType(Type.valueOf(productRequest.getType()));
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setCreated_date(productRequest.getCreated_date());
        productRepository.save(product);
    }

    @Override
    public ProductResponse getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty())
            throw new NotFoundException("Product doesn't exist!", HttpStatus.BAD_REQUEST);
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.get().getId());
        productResponse.setName(product.get().getName());
        productResponse.setType(product.get().getType());
        productResponse.setPrice(product.get().getPrice());
        productResponse.setDescription(product.get().getDescription());
        productResponse.setCreated_date(product.get().getCreated_date());
        return productResponse;
    }

    @Override
    public void deleteById(Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty())
            throw new NotFoundException("Product doesn't exist!", HttpStatus.BAD_REQUEST);
        productRepository.deleteById(id);
    }

    @Override
    public void updateById(Long id, ProductRequest productRequest){
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty())
            throw new NotFoundException("Product doesn't exist!", HttpStatus.BAD_REQUEST);
        product.get().setName(productRequest.getName());
        product.get().setType(Type.valueOf(productRequest.getType()));
        product.get().setCreated_date(productRequest.getCreated_date());
        product.get().setPrice(productRequest.getPrice());
        product.get().setDescription(productRequest.getDescription());
        productRepository.save(product.get());
    }

}
