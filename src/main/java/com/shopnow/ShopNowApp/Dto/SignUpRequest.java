package com.shopnow.ShopNowApp.Dto;
import lombok.Data;

@Data
public class SignUpRequest {


    private String username;
    private String password;
    private String name;
    private String email;
}
