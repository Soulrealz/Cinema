package com.fmi.cinema.cinema.model.dto.projectionDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ProjectionDto(
        Long id,
        @NotNull(message = "Room ID must not be empty!") Long roomId,
        @NotNull(message = "Movie ID must not be empty!") Long movieId,
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonProperty("projectionTime")
        @NotNull(message = "Projection time must not be empty!") LocalDateTime projectionTime) { }