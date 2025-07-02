package com.example.demo.service;

import com.example.demo.dto.MovieDto;
import com.example.demo.entity.Movie;
import com.example.demo.repository.MovieRepository;
import com.example.demo.request.AddMovieRequest;
import com.example.demo.request.RatingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    public List<MovieDto> getAllMovies() {
        return movieRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<MovieDto> searchTop3(String filter, String keyword) {
        if (filter.equals("titles")){
            List<Movie> result = movieRepository.searchTop3ByTitle(keyword);
            return result.stream()
                    .map(this::toDto)
                    .toList();
        } else if (filter.equals("celebs")) {
            List<Movie> result = movieRepository.searchTop3ByActor(keyword);
            return result.stream()
                    .map(this::toDto)
                    .toList();
        } else if (filter.equals("all")){
            List<Movie> result = movieRepository.searchTop3ByAll(keyword);
            return result.stream()
                    .map(this::toDto)
                    .toList();
        }

        return null;
    }

    public MovieDto getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film bulunamadı."));
        return toDto(movie);
    }

    public List<MovieDto> getTop10Movies() {
        return movieRepository.findTop10ByOrderByRatingDesc()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
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

    public void addMovie(AddMovieRequest request) {
        Movie movie = new Movie();
        movie.setTitle(request.getTitle());
        movie.setSummary(request.getSummary());
        movie.setActors(request.getActors());
        movie.setRating(0.0);
        movie.setPopularity(0.0);
        movie.setTrailerUrl(request.getTrailerUrl());
        movie.setPosterUrl(request.getPosterUrl());
        movie.setReleaseDate(request.getReleaseDate());

        movieRepository.save(movie);
    }

}
