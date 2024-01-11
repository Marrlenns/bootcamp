package com.example.demo.services.imp;

import com.example.demo.dto.product.*;
import com.example.demo.entities.Product;
import com.example.demo.enums.Type;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public boolean checkType(String s){
        for (Type type: Type.values())
            if (type.name().equalsIgnoreCase(s))
                return true;

        return false;
    }

    @Override
    public void register(ProductRequest productRequest){
        Product product = new Product();

        if(!checkType(productRequest.getType())) {
            throw new BadRequestException("This type doesn't exist!");
        }
        
        product.setName(productRequest.getName());
        product.setType(Type.valueOf(productRequest.getType()));
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setCreated_date(productRequest.getCreated_date());
        productRepository.save(product);
    }

    @Override
    public List<ProductResponse> findAll(){
        List<Product> products = productRepository.findAll();
        return productMapper.toDtos(products);
    }

    @Override
    public ProductDetailResponse getById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isEmpty())
            throw new NotFoundException("Product doesn't exist!", HttpStatus.BAD_REQUEST);
        return productMapper.toDetailDto(product.get());
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
        if(!checkType(productRequest.getType()))
            throw new BadRequestException("You can't change to this type!");
        product.get().setType(Type.valueOf(productRequest.getType()));
        product.get().setCreated_date(productRequest.getCreated_date());
        product.get().setPrice(productRequest.getPrice());
        product.get().setDescription(productRequest.getDescription());
        productRepository.save(product.get());
    }

}
