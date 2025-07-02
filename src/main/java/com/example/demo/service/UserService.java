package com.example.demo.service;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.Movie;
import com.example.demo.entity.User;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    public List<MovieDto> getWatchlist(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));

        return user.getWatchList().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void addToWatchlist(Long userId, Long movieId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Film bulunamadı."));

        if (!user.getWatchList().contains(movie)) {
            user.getWatchList().add(movie);
            userRepository.save(user);
        }
    }

    private MovieDto toDto(Movie movie) {
        MovieDto dto = new MovieDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setSummary(movie.getSummary());
        dto.setActors(movie.getActors());
        dto.setPosterUrl(movie.getPosterUrl());
        dto.setTrailerUrl(movie.getTrailerUrl());
        dto.setRating(movie.getRating());
        dto.setPopularity(movie.getPopularity());
        dto.setReleaseDate(movie.getReleaseDate().toString());
        return dto;
    }

}
