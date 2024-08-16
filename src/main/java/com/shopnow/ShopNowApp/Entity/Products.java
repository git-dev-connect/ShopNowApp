package com.shopnow.ShopNowApp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    private String productName;
    private String mrpPrice;
    private String price;
    private String discountPercentage;
    private String catagory;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String quantity;
    private String soldBy;
    private String imageUrl;
//    future use
//    private String replacement;


}
