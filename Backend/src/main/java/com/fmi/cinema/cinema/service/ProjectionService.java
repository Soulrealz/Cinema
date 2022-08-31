package com.fmi.cinema.cinema.service;

import com.fmi.cinema.cinema.exceptions.BadRequestException;
import com.fmi.cinema.cinema.exceptions.ProjectionAlreadyExistsException;
import com.fmi.cinema.cinema.mapper.ProjectionDtoMapper;
import com.fmi.cinema.cinema.model.Projection;
import com.fmi.cinema.cinema.model.dto.projectionDTO.ProjectionDto;
import com.fmi.cinema.cinema.repository.ProjectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class ProjectionService
{

    private final ProjectionRepository projectionRepository;
    private final ProjectionDtoMapper projectionDtoMapper;
    private final SessionManager sessionManager;
    private final long ADMIN_ID = 1;

    @Autowired
    public ProjectionService(final ProjectionRepository projectionRepository,
                             final ProjectionDtoMapper projectionDtoMapper,
                             final SessionManager sessionManager) {
        this.projectionRepository = projectionRepository;
        this.projectionDtoMapper = projectionDtoMapper;
        this.sessionManager = sessionManager;
    }


    public Page<ProjectionDto> findAll(final Pageable pageable) {
        return projectionRepository.findAll(pageable).map(projectionDtoMapper::convertToDto);
    }

    public Page<ProjectionDto> findAll(final LocalDateTime selectedDateTime, final Pageable pageable) {
        return projectionRepository.findAll(selectedDateTime, pageable).map(projectionDtoMapper::convertToDto);
    }

    public Page<ProjectionDto> findAll(final Long selectedMovieId, final Pageable pageable) {
        return projectionRepository.findAll(selectedMovieId, pageable).map(projectionDtoMapper::convertToDto);
    }

    public Page<ProjectionDto> findAll(final LocalDateTime fromDateTime,
                                       final LocalDateTime toDateTime,
                                       final Pageable pageable) {
        return projectionRepository.findAll(fromDateTime, toDateTime, pageable).map(projectionDtoMapper::convertToDto);
    }

    public ProjectionDto addProjection(final ProjectionDto request, final HttpSession session) {
        if(sessionManager.checkIfThereIsLoggedUser(session).isEmpty()) {
            throw new BadRequestException("Login first before adding projections!");
        } else if (sessionManager.getUserIdFromSession(session) != ADMIN_ID) {
            throw new BadRequestException("You have no permission to add projections!");
        }

        final Projection projectionToSave = projectionDtoMapper.convertToEntity(request);
        final Projection savedProjection = projectionRepository.save(projectionToSave);

        return projectionDtoMapper.convertToDto(savedProjection);
    }

    public ProjectionDto updateProjection(final ProjectionDto request, final HttpSession session) {
        if(sessionManager.checkIfThereIsLoggedUser(session).isEmpty()) {
            throw new BadRequestException("Login first before updating projections!");
        } else if (sessionManager.getUserIdFromSession(session) != ADMIN_ID) {
            throw new BadRequestException("You have no permission to update projections!");
        }

        projectionRepository.findById(request.id()).orElseThrow(() ->
                new NoSuchElementException("No projection found to update!"));

        final Projection updatedProjectionToSave = projectionDtoMapper.convertToEntity(request);
        final Projection savedProjection = projectionRepository.save(updatedProjectionToSave);

        return projectionDtoMapper.convertToDto(savedProjection);
    }

    public void removeProjection(final Long requestId, final HttpSession session) {
        if(sessionManager.checkIfThereIsLoggedUser(session).isEmpty()) {
            throw new BadRequestException("Login first before deleting projections!");
        } else if (sessionManager.getUserIdFromSession(session) != ADMIN_ID) {
            throw new BadRequestException("You have no permission to delete projections!");
        }

        projectionRepository.findById(requestId).orElseThrow(() ->
                new NoSuchElementException("No projection found to delete!"));

        projectionRepository.deleteById(requestId);
    }
}