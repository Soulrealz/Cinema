package com.fmi.cinema.cinema.controller;

import com.fmi.cinema.cinema.model.Movie;
import com.fmi.cinema.cinema.model.dto.moviedto.MovieDto;
import com.fmi.cinema.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(final MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public MovieDto getMovieById(@PathVariable final Long id) {
        return movieService.findById(id);
    }

    @GetMapping("/search")
    public Page<MovieDto> getAllMoviesFound(
            @RequestParam final String keyword,
            @RequestParam final Integer page,
            @RequestParam final Integer size) {

        return movieService.findAll(keyword, PageRequest.of(page, size));
    }

    @GetMapping("/all")
    public Page<MovieDto> getAllMovies(
            @RequestParam final Integer page,
            @RequestParam final Integer size,
            @RequestParam final String sortBy,
            @RequestParam final String sort) {

        return movieService.findAll(
                PageRequest.of(
                        page,
                        size,
                        sort.equalsIgnoreCase("descending") ?
                                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending()));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDto addMovie(@Valid @RequestBody final MovieDto requestDto, final HttpSession session) {
        return movieService.addMovie(requestDto, session);
    }

    @PutMapping
    public MovieDto updateMovie(@Valid @RequestBody final MovieDto requestDto, final HttpSession session) {
        return movieService.updateMovie(requestDto, session);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable final Long id, final HttpSession session) {
        movieService.removeMovie(id, session);
    }
}