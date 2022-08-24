package com.fmi.cinema.cinema.service;

import com.fmi.cinema.cinema.repository.ProjectionRepository;

public class ProjectionService
{

    private final ProjectionRepository projectionRepository;

    public ProjectionService(final ProjectionRepository repo)
    {
        projectionRepository = repo;
    }
}
