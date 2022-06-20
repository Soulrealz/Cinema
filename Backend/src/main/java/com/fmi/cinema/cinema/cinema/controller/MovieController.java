package com.fmi.cinema.cinema.cinema.controller;

import com.fmi.cinema.cinema.cinema.model.Movie;
import com.fmi.cinema.cinema.cinema.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController
{

	private final MovieService movieService;

	public MovieController(final MovieService movieService)
	{
		this.movieService = movieService;
	}

	@GetMapping
	public List<Movie> getAllMovies()
	{
		return movieService.getAllMovies();
	}
}