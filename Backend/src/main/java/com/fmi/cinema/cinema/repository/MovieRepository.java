package com.fmi.cinema.cinema.repository;

import com.fmi.cinema.cinema.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findById(final Long id);

    Optional<Movie> findByName(final String name);

    @Query("""
           SELECT m FROM Movie m
           WHERE CONCAT(m.name, ' ', m.duration, ' ', m.genre, ' ', m.year)
           LIKE %:keyword% ORDER BY m.name ASC
           """)
    Page<Movie> findAll(@Param("keyword") final String keyword, final Pageable pageable);

    Page<Movie> findAll(final Pageable pageable);

    List<Movie> findAll();
}