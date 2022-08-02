package com.fmi.cinema.cinema.model.dto.moviedto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public record MovieDto(
        Long id,
        @NotBlank(message = "Movie name must not be empty!") String name,
        @NotNull(message = "Duration must not be empty!") Long duration,
        @NotBlank(message = "Genre must not be empty!") String genre,
        @NotNull(message = "Year must not be empty!") LocalDate year) { }