package com.fmi.cinema.cinema.service;

import com.fmi.cinema.cinema.exceptions.BadRequestException;
import com.fmi.cinema.cinema.exceptions.MovieAlreadyExistsException;
import com.fmi.cinema.cinema.mapper.MovieDtoMapper;
import com.fmi.cinema.cinema.model.Movie;
import com.fmi.cinema.cinema.model.dto.moviedto.MovieDto;
import com.fmi.cinema.cinema.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieDtoMapper movieDtoMapper;
    private final SessionManager sessionManager;
    private final long ADMIN_ID = 1;

    @Autowired
    public MovieService(final MovieRepository movieRepository,
                        final MovieDtoMapper movieDtoMapper,
                        final SessionManager sessionManager) {
        this.movieRepository = movieRepository;
        this.movieDtoMapper = movieDtoMapper;
        this.sessionManager = sessionManager;
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
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

    public MovieDto addMovie(final MovieDto request, final HttpSession session) {
        if(sessionManager.checkIfThereIsLoggedUser(session).isEmpty()) {
            throw new BadRequestException("Login first before adding movies!");
        } else if (sessionManager.getUserIdFromSession(session) != ADMIN_ID) {
            throw new BadRequestException("You have no permission to add movies!");
        }


        if (movieRepository.findByName(request.name()).isPresent()) {
            throw new MovieAlreadyExistsException("Movie with that name already exists!");
        }

        final Movie movieToSave = movieDtoMapper.convertToEntity(request);
        final Movie savedMovie = movieRepository.save(movieToSave);

        return movieDtoMapper.convertToDto(savedMovie);
    }

    public MovieDto updateMovie(final MovieDto request, final HttpSession session) {
        if(sessionManager.checkIfThereIsLoggedUser(session).isEmpty()) {
            throw new BadRequestException("Login first before updating movies!");
        } else if (sessionManager.getUserIdFromSession(session) != ADMIN_ID) {
            throw new BadRequestException("You have no permission to update movies!");
        }

        movieRepository.findById(request.id()).orElseThrow(() ->
                new NoSuchElementException("No movie found to update!"));

        final Movie updatedMovieToSave = movieDtoMapper.convertToEntity(request);
        final Movie savedMovie = movieRepository.save(updatedMovieToSave);

        return movieDtoMapper.convertToDto(savedMovie);
    }

    public void removeMovie(final Long requestId, final HttpSession session) {
        if(sessionManager.checkIfThereIsLoggedUser(session).isEmpty()) {
            throw new BadRequestException("Login first before deleting movies!");
        } else if (sessionManager.getUserIdFromSession(session) != ADMIN_ID) {
            throw new BadRequestException("You have no permission to delete movies!");
        }

        movieRepository.findById(requestId).orElseThrow(() ->
                new NoSuchElementException("No movie found to delete!"));

        movieRepository.deleteById(requestId);
    }
}