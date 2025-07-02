package com.example.demo.request;

import lombok.Data;

@Data
public class RatingRequest {

    private Long userId;

    private int rating;

    private String comment;
}
