package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieDetailDto {
    private MovieDto movie;
    private List<CommentDto> comments;
}
