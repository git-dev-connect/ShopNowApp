package com.shopnow.ShopNowApp.serviceImpl;

import com.shopnow.ShopNowApp.Dto.ProductDto;
import com.shopnow.ShopNowApp.Entity.Products;
import com.shopnow.ShopNowApp.Entity.User;
import com.shopnow.ShopNowApp.Repo.ProductRepository;
import com.shopnow.ShopNowApp.Repo.UserRepository;
import com.shopnow.ShopNowApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProduct() {

        List<Products> products = productRepository.getAllProducts();
        System.out.println("Total no of records in db: "+ products.size());
        List<ProductDto> productDtos = products.stream().map(product -> {
            ProductDto dto = new ProductDto();
            dto.setProductName(product.getProductId());
            dto.setProductName(product.getProductName());
            dto.setProductPrice(product.getPrice());
            dto.setProductDescription(product.getDescription());
            dto.setImageUrl(product.getImageUrl());
            return dto;
        }).collect(Collectors.toList());


        return productDtos;
    }
}
