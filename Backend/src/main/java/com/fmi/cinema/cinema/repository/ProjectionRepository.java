package com.fmi.cinema.cinema.repository;

import com.fmi.cinema.cinema.model.Projection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectionRepository extends JpaRepository<Projection, Long>
{
    Optional<Projection> findById(final Long id);
}
