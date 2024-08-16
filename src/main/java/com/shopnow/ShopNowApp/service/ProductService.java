package com.shopnow.ShopNowApp.service;

import com.shopnow.ShopNowApp.Dto.ProductDto;
import com.shopnow.ShopNowApp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductService {


    List<ProductDto> getAllProduct();
}
