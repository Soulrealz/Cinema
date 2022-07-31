package com.fmi.cinema.cinema.mapper;

import com.fmi.cinema.cinema.model.Movie;
import com.fmi.cinema.cinema.model.dto.moviedto.MovieDto;
import org.springframework.stereotype.Component;

@Component
public class MovieDtoMapper {

    public Movie convertToEntity(final MovieDto movieDto) {
        return new Movie(
                movieDto.getId(),
                movieDto.getName(),
                movieDto.getDuration(),
                movieDto.getGenre(),
                movieDto.getYear());
    }

    public MovieDto convertToDto(final Movie movie) {
        return new MovieDto(
                movie.getId(),
                movie.getName(),
                movie.getDuration(),
                movie.getGenre(),
                movie.getYear());
    }
}