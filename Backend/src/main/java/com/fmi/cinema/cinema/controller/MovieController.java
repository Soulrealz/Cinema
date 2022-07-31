package com.fmi.cinema.cinema.controller;

import com.fmi.cinema.cinema.model.dto.moviedto.MovieDto;
import com.fmi.cinema.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    public MovieDto getMovieById(@PathVariable final Long id) {
        return movieService.findById(id);
    }

    @GetMapping
    public Page<MovieDto> getAllMovies(Pageable pageable) {
        return movieService.findAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto addMovie(@Valid @RequestBody final MovieDto requestDto) {
        return movieService.addMovie(requestDto);
    }

    @PutMapping
    public MovieDto updateMovie(@Valid @RequestBody final MovieDto requestDto) {
        return movieService.updateMovie(requestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable final Long id) {
        movieService.removeMovie(id);
    }
}