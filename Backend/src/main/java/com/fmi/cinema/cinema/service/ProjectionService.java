package com.fmi.cinema.cinema.service;

import com.fmi.cinema.cinema.repository.ProjectionRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectionService
{

    private final ProjectionRepository projectionRepository;

    public ProjectionService(final ProjectionRepository repo)
    {
        projectionRepository = repo;
    }
}
