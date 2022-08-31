package com.fmi.cinema.cinema.mapper;

import com.fmi.cinema.cinema.model.Projection;
import com.fmi.cinema.cinema.model.dto.projectionDTO.ProjectionDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectionDtoMapper {

    public Projection convertToEntity(final ProjectionDto projectionDto) {
        return new Projection(
                projectionDto.id(),
                projectionDto.roomId(),
                projectionDto.movieId(),
                projectionDto.projectionTime());
    }

    public ProjectionDto convertToDto(final Projection projection) {
        return new ProjectionDto(
                projection.getId(),
                projection.getRoomId(),
                projection.getMovieId(),
                projection.getProjectionTime());
    }
}