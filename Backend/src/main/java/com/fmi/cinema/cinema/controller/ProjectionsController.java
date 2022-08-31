package com.fmi.cinema.cinema.controller;

import com.fmi.cinema.cinema.model.Movie;
import com.fmi.cinema.cinema.model.Projection;
import com.fmi.cinema.cinema.model.dto.moviedto.MovieDto;
import com.fmi.cinema.cinema.model.dto.projectionDTO.ProjectionDto;
import com.fmi.cinema.cinema.service.ProjectionService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/projections")
public class ProjectionsController {

    private final ProjectionService projectionService;

    @Autowired
    public ProjectionsController(final ProjectionService projectionService) {
        this.projectionService = projectionService;
    }

    @GetMapping
    public Page<ProjectionDto> getAllProjectionsForToday(@RequestParam final Integer page,
                                                         @RequestParam final Integer size) {
        return projectionService.findAll(PageRequest.of(page, size));
    }

    @GetMapping("/searchByDate")
    public Page<ProjectionDto> getAllProjectionsForSelectedTime(@RequestParam final LocalDateTime selectedDateTime,
                                                                @RequestParam final Integer page,
                                                                @RequestParam final Integer size) {
        return projectionService.findAll(selectedDateTime, PageRequest.of(page, size));
    }

    @GetMapping("/searchByName")
    public Page<ProjectionDto> getAllProjectionsForSelectedName(@RequestParam final Long selectedMovieId,
                                                                @RequestParam final Integer page,
                                                                @RequestParam final Integer size) {
        return projectionService.findAll(selectedMovieId, PageRequest.of(page, size));
    }

    @GetMapping("/searchBetweenDates")
    public Page<ProjectionDto> getAllProjectionsBetweenSelectedDates(@RequestParam final LocalDateTime fromDateTime,
                                                                     @RequestParam final LocalDateTime toDateTime,
                                                                     @RequestParam final Integer page,
                                                                     @RequestParam final Integer size) {
        return projectionService.findAll(fromDateTime, toDateTime, PageRequest.of(page, size));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectionDto addProjection(@Valid @RequestBody final ProjectionDto requestDto,
                                       final HttpSession session) {
        return projectionService.addProjection(requestDto, session);
    }

    @PutMapping
    public ProjectionDto updateProjection(@Valid @RequestBody final ProjectionDto requestDto,
                                          final HttpSession session) {
        return projectionService.updateProjection(requestDto, session);
    }

    @DeleteMapping("/{id}")
    public void deleteProjection(@PathVariable final Long id, final HttpSession session) {
        projectionService.removeProjection(id, session);
    }

    //get for today
    //post create new projection
    //delete projection
}
