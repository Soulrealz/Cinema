package com.fmi.cinema.cinema.service;

import com.fmi.cinema.cinema.exceptions.MovieAlreadyExistsException;
import com.fmi.cinema.cinema.mapper.MovieDtoMapper;
import com.fmi.cinema.cinema.model.Movie;
import com.fmi.cinema.cinema.model.dto.moviedto.MovieDto;
import com.fmi.cinema.cinema.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieDtoMapper movieDtoMapper;

    @Autowired
    public MovieService(final MovieRepository movieRepository, final MovieDtoMapper movieDtoMapper) {
        this.movieRepository = movieRepository;
        this.movieDtoMapper = movieDtoMapper;
    }

    public MovieDto findById(final Long id) {
        final Movie savedMovie = movieRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("No movie found!"));

        return movieDtoMapper.convertToDto(savedMovie);
    }

    public Page<MovieDto> findAll(final Pageable pageable) {
        return movieRepository.findAll(pageable).map(movieDtoMapper::convertToDto);
    }

    public MovieDto addMovie(final MovieDto movieDto) {
        final Movie movie = movieDtoMapper.convertToEntity(movieDto);

        if (movieRepository.findByName(movie.getName()).isPresent()) {
            throw new MovieAlreadyExistsException("Movie with that name already exists!");
        }

        final Movie savedMovie = movieRepository.save(movie);
        return movieDtoMapper.convertToDto(savedMovie);
    }

    public MovieDto updateMovie(final MovieDto movieDto) {
        final Movie oldMovie = movieRepository.findById(movieDto.getId()).orElseThrow(() ->
                new RuntimeException("No movie found to update!"));

        final Movie updatedMovie = movieDtoMapper.convertToEntity(movieDto);
        final Movie savedMovie = movieRepository.save(updatedMovie);

        return movieDtoMapper.convertToDto(savedMovie);
    }

    public void removeMovie(final Long id) {
        final Movie savedMovie = movieRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("No movie found to delete!"));

        movieRepository.deleteById(id);
    }
}