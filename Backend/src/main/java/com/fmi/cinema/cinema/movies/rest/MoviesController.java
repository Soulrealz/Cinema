package com.fmi.cinema.cinema.movies.rest;

import com.fmi.cinema.cinema.movies.model.Movie;
import com.fmi.cinema.cinema.movies.service.MoviesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController
{

	private final MoviesService moviesService;

	public MoviesController(final MoviesService moviesService)
	{
		this.moviesService = moviesService;
	}

	@GetMapping
	public List<Movie> getAllMovies()
	{
		return moviesService.getAllMovies();
	}
}
