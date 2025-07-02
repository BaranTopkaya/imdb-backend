package com.example.demo.request;

import com.example.demo.entity.Comment;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class AddMovieRequest {
    private String title;
    private String summary;
    private List<String> actors;
    private String posterUrl;
    private String trailerUrl;
    private LocalDate releaseDate;
}
