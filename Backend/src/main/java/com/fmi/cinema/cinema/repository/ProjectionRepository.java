package com.fmi.cinema.cinema.repository;

import com.fmi.cinema.cinema.model.Projection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectionRepository extends JpaRepository<Projection, Long>
{
}
