package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private String surname;
    private String country;
    private String city;
    private String photoUrl;
    private List<Long> watchListMovieIds;
}
