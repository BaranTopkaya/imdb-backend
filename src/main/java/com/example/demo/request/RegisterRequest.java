package com.example.demo.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String country;
    private String city;
    private String imageUrl;
}
