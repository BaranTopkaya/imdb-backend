package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Movie;
import com.example.demo.entity.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.RatingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public void rateMovie(Long movieId, RatingRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Film bulunamadı"));

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setMovie(movie);
        comment.setRating(request.getRating());
        comment.setContent(request.getComment());

        commentRepository.save(comment);

        List<Comment> allComments = commentRepository.findByMovieId(movieId);
        double avg = allComments.stream()
                .mapToInt(Comment::getRating)
                .average()
                .orElse(0.0);
        movie.setRating(avg);
        movieRepository.save(movie);
    }

    public List<CommentDto> getCommentsByMovie(Long movieId) {
        return commentRepository.findByMovieId(movieId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private CommentDto toDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setRating(comment.getRating());
        dto.setContent(comment.getContent());
        dto.setCreatedAt(comment.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        dto.setUserId(comment.getUser().getId());
        dto.setUserName(comment.getUser().getName() + " " + comment.getUser().getSurname());
        dto.setMovieId(comment.getMovie().getId());
        dto.setCountry(comment.getUser().getCountry());
        return dto;
    }
}
