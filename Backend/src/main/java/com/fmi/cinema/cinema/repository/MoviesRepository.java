package com.fmi.cinema.cinema.repository;

import com.fmi.cinema.cinema.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Integer>
{

	List<Movie> findTop7ByOrderByYearDesc();

	Optional<Movie> findById(final Long id);

	Optional<Movie> findByName(final String name);
}
