package com.example.demo.repository;

import com.example.demo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findTop10ByOrderByRatingDesc();

    @Query("""
    SELECT m FROM Movie m 
    JOIN m.actors a 
    WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :keyword, '%')) 
       OR LOWER(m.summary) LIKE LOWER(CONCAT('%', :keyword, '%')) 
       OR LOWER(a) LIKE LOWER(CONCAT('%', :keyword, '%'))
    GROUP BY m
    ORDER BY m.id DESC
""")
    List<Movie> searchTop3(@Param("keyword") String keyword);


    @Query(value = """
SELECT * FROM movies m
WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
   OR LOWER(m.summary) LIKE LOWER(CONCAT('%', :keyword, '%'))
ORDER BY m.id DESC
LIMIT 3
""", nativeQuery = true)
    List<Movie> searchTop3ByTitle(@Param("keyword") String keyword);

    @Query(value = """
SELECT DISTINCT m.* FROM movies m
JOIN movie_actors ma ON m.id = ma.movie_id
WHERE LOWER(ma.actor) LIKE LOWER(CONCAT('%', :keyword, '%'))
ORDER BY m.id DESC
LIMIT 3
""", nativeQuery = true)
    List<Movie> searchTop3ByActor(@Param("keyword") String keyword);

    @Query(value = """
SELECT DISTINCT m.* FROM movies m
LEFT JOIN movie_actors ma ON m.id = ma.movie_id
WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
   OR LOWER(m.summary) LIKE LOWER(CONCAT('%', :keyword, '%'))
   OR LOWER(ma.actor) LIKE LOWER(CONCAT('%', :keyword, '%'))
ORDER BY m.id DESC
LIMIT 3
""", nativeQuery = true)
    List<Movie> searchTop3ByAll(@Param("keyword") String keyword);



}
