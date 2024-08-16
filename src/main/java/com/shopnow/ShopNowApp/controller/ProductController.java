package com.shopnow.ShopNowApp.controller;

import com.shopnow.ShopNowApp.Dto.ProductDto;
import com.shopnow.ShopNowApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {


    @Autowired
    ProductService productService;

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        System.out.println("getAllProducts - inside #####################");
        List<ProductDto> productDto = productService.getAllProduct();
        return ResponseEntity.ok(productDto);
    }
}
