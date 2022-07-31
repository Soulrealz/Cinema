package com.fmi.cinema.cinema.repository;

import com.fmi.cinema.cinema.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findById(final Long id);

    Optional<Movie> findByName(String name);

    List<Movie> findAll();
}