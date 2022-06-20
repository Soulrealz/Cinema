package com.fmi.cinema.cinema.service;

import com.fmi.cinema.cinema.model.Movie;
import com.fmi.cinema.cinema.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieService
{

	private final MovieRepository movieRepository;

	public MovieService(final MovieRepository moviesRepository)
	{
		this.movieRepository = moviesRepository;
	}

	public List<Movie> getAllMovies()
	{
		return movieRepository.findAll();
	}
}
