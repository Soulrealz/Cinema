package com.fmi.cinema.cinema.movies.repository;

import com.fmi.cinema.cinema.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Integer>
{

}
