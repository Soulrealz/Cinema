package com.fmi.cinema.cinema.service;

import com.fmi.cinema.cinema.model.Movie;
import com.fmi.cinema.cinema.model.MovieCoreInfoDto;
import com.fmi.cinema.cinema.model.MovieDto;
import com.fmi.cinema.cinema.repository.MoviesRepository;
import com.fmi.cinema.cinema.security.SecurityService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoviesService
{

	private final MoviesRepository moviesRepository;

	private final SecurityService securityService;

	public MoviesService(final MoviesRepository moviesRepository,
						 final SecurityService securityService)
	{
		this.moviesRepository = moviesRepository;
		this.securityService = securityService;
	}

	public List<Movie> getAllMovies()
	{
		return moviesRepository.findAll();
	}

	public Movie getMovieInfoById(final Long id)
	{
		return moviesRepository.findById(id)
							   .orElseThrow(() -> new RuntimeException("No movie found"));
	}

	public List<MovieCoreInfoDto> getActiveBroadcastMovies()
	{
		final List<Movie> activelyBroadcastMovies = moviesRepository.findTop7ByOrderByYearDesc();

		return activelyBroadcastMovies.stream()
									  .map(movie -> new MovieCoreInfoDto(movie.getName(),
																		 movie.getDuration(),
																		 movie.getGenre()))
									  .collect(Collectors.toList());
	}

	public void addMovie(final MovieDto movieAdditionRequest)
	{
		final String userName = securityService.getActor();
		//todo validate is admin

		final Movie movie = buildMovieForAddition(movieAdditionRequest);
		moviesRepository.save(movie);
	}

	public void updateMovie(final MovieDto movieUpdateRequest)
	{
		final String userName = securityService.getActor();
		//todo validate is admin

		final Movie movie = moviesRepository.findByName(movieUpdateRequest.getName())
											.orElseThrow(() -> new RuntimeException("No movie found"));

		final Movie updateMovie = buildMovieForUpdate(movie, movieUpdateRequest);
		moviesRepository.save(updateMovie);
	}

	private Movie buildMovieForAddition(final MovieDto request)
	{
		final Movie movie = new Movie();
		movie.setName(request.getName());
		movie.setDuration(request.getDuration());
		movie.setGenre(request.getGenre());
		movie.setYear(request.getYear());

		return movie;
	}

	public Movie buildMovieForUpdate(final Movie oldMovie,
									 final MovieDto request)
	{
		final Movie updatedMovie = new Movie();
		updatedMovie.setId(oldMovie.getId());

		if (request.getName() != null)
		{
			updatedMovie.setName(request.getName());
		}

		if (request.getGenre() != null)
		{
			updatedMovie.setGenre(request.getGenre());
		}

		if (request.getDuration() != null)
		{
			updatedMovie.setDuration(request.getDuration());
		}

		if (request.getYear() != null)
		{
			updatedMovie.setYear(request.getYear());
		}

		return updatedMovie;
	}
}
