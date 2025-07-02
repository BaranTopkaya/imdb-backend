package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.Movie;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.LoginRequest;
import com.example.demo.request.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    public UserDto login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Şifre yanlış.");
        }

        return toDto(user);
    }

    public UserDto register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Bu email zaten kayıtlı.");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // encode edilmiyor: demo
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setCountry(request.getCountry());
        user.setCity(request.getCity());
        user.setPhotoUrl(request.getImageUrl());

        return toDto(userRepository.save(user));
    }

    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        dto.setCountry(user.getCountry());
        dto.setCity(user.getCity());
        dto.setPhotoUrl(user.getPhotoUrl());

        dto.setWatchListMovieIds(
                user.getWatchList()
                        .stream()
                        .map(Movie::getId)
                        .collect(Collectors.toList())
        );

        return dto;
    }
}
