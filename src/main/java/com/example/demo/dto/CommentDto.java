package com.example.demo.dto;

import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private int rating;
    private String country;
    private String content;
    private String createdAt;
    private Long userId;
    private String userName;
    private Long movieId;
}
