package com.fmi.cinema.cinema.repository;

import com.fmi.cinema.cinema.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long>
{
    Optional<Seat> findById(final Long id);
}
