package com.example.demo.controller;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.Movie;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}/watchlist")
    public ResponseEntity<List<MovieDto>> getWatchlist(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getWatchlist(id));
    }

    @PostMapping("/{id}/watchlist/{movieId}")
    public ResponseEntity<?> addToWatchlist(@PathVariable Long id, @PathVariable Long movieId) {
        userService.addToWatchlist(id, movieId);
        return ResponseEntity.ok().build();
    }
}
