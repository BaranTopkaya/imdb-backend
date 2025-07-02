package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import com.example.demo.request.RatingRequest;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{movieId}/rate")
    public ResponseEntity<?> rateMovie(@PathVariable Long movieId, @RequestBody RatingRequest request) {
        commentService.rateMovie(movieId, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable Long movieId) {
        return ResponseEntity.ok(commentService.getCommentsByMovie(movieId));
    }
}
