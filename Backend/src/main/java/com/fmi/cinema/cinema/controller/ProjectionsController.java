package com.fmi.cinema.cinema.controller;

import com.fmi.cinema.cinema.service.ProjectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projections")
public class ProjectionsController {

    private final ProjectionService projectionService;

    @Autowired
    public ProjectionsController(final ProjectionService projectionService) {
        this.projectionService = projectionService;
    }
}
