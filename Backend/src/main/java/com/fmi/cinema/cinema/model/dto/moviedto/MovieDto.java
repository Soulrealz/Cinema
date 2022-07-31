package com.fmi.cinema.cinema.model.dto.moviedto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class MovieDto {
    private Long id;
    @NotBlank(message = "Movie name must not be empty")
    private String name;
    @NotNull(message = "Duration must not be empty")
    private Long duration;
    @NotBlank(message = "Genre must not be empty")
    private String genre;
    @NotNull(message = "Year must not be empty")
    private LocalDate year;

    public MovieDto() {}

    public MovieDto(Long id, String name, Long duration, String genre, LocalDate year) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.genre = genre;
        this.year = year;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }

    public LocalDate getYear() {
        return year;
    }
}