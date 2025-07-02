package com.example.demo.controller;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.Movie;
import com.example.demo.request.AddMovieRequest;
import com.example.demo.request.RatingRequest;
import com.example.demo.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/add")
    public void addMovie(@RequestBody AddMovieRequest request) {
        movieService.addMovie(request);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDto>> searchMovies(@RequestParam String filter, @RequestParam String query) {
        return ResponseEntity.ok(movieService.searchTop3(filter, query));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping("/getTop")
    public ResponseEntity<List<MovieDto>> getTop10Movies() {
        return ResponseEntity.ok(movieService.getTop10Movies());
    }
}
