package com.fmi.cinema.cinema.movies.service;

import com.fmi.cinema.cinema.movies.model.Movie;
import com.fmi.cinema.cinema.movies.repository.MoviesRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MoviesService
{

	private final MoviesRepository moviesRepository;

	public MoviesService(final MoviesRepository moviesRepository)
	{
		this.moviesRepository = moviesRepository;
	}

	public List<Movie> getAllMovies()
	{
		return moviesRepository.findAll();
	}
}
