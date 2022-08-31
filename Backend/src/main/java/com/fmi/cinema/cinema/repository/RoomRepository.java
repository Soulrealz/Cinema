package com.fmi.cinema.cinema.repository;

import com.fmi.cinema.cinema.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>
{
    Optional<Room> findById(final Long id);
}
