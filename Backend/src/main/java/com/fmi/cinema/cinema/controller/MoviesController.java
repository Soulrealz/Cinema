package com.fmi.cinema.cinema.controller;

import com.fmi.cinema.cinema.model.Movie;
import com.fmi.cinema.cinema.model.MovieCoreInfoDto;
import com.fmi.cinema.cinema.model.MovieDto;
import com.fmi.cinema.cinema.security.SecurityService;
import com.fmi.cinema.cinema.service.MoviesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("/active-broadcast")
	public List<MovieCoreInfoDto> getActiveBroadcastMovies()
	{
		return moviesService.getActiveBroadcastMovies();
	}

	@GetMapping("/id/info")
	public Movie getMovieInfo(final Long id)
	{
		return moviesService.getMovieInfoById(id);
	}

	@PostMapping
	public void addMovie(@RequestBody final MovieDto movie)
	{

		moviesService.addMovie(movie);
	}

	@PutMapping
	public void updateMovie(@RequestBody final MovieDto updateRequest)
	{
		moviesService.updateMovie(updateRequest);
	}

//	@GetMapping("/filtered")
//	public List<Movie> filteredMovies()
//	{
//
//	}
}