package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieDto {
    private Long id;
    private String title;
    private String summary;
    private List<String> actors;
    private String posterUrl;
    private String trailerUrl;
    private Double rating;
    private Double popularity;
    private String releaseDate;
}
