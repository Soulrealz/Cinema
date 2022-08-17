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

    public MovieDto findById(final Long requestId) {
        final Movie savedMovie = movieRepository.findById(requestId).orElseThrow(() ->
                new NoSuchElementException("No movie found!"));

        return movieDtoMapper.convertToDto(savedMovie);
    }

    public Page<MovieDto> findAll(final String keyword, final Pageable pageable) {
        return movieRepository.findAll(keyword, pageable).map(movieDtoMapper::convertToDto);
    }


    public Page<MovieDto> findAll(final Pageable pageable) {
        return movieRepository.findAll(pageable).map(movieDtoMapper::convertToDto);
    }

    public MovieDto addMovie(final MovieDto request) {
        if (movieRepository.findByName(request.name()).isPresent()) {
            throw new MovieAlreadyExistsException("Movie with that name already exists!");
        }

        final Movie movieToSave = movieDtoMapper.convertToEntity(request);
        final Movie savedMovie = movieRepository.save(movieToSave);

        return movieDtoMapper.convertToDto(savedMovie);
    }

    public MovieDto updateMovie(final MovieDto request) {
        movieRepository.findById(request.id()).orElseThrow(() ->
                new NoSuchElementException("No movie found to update!"));

        final Movie updatedMovieToSave = movieDtoMapper.convertToEntity(request);
        final Movie savedMovie = movieRepository.save(updatedMovieToSave);

        return movieDtoMapper.convertToDto(savedMovie);
    }

    public void removeMovie(final Long requestId) {
        movieRepository.findById(requestId).orElseThrow(() ->
                new NoSuchElementException("No movie found to delete!"));

        movieRepository.deleteById(requestId);
    }
}