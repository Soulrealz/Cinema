package com.fmi.cinema.cinema.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "projections")
public class Projection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long roomId;

    @Column
    private Long movieId;

    @Column
    private LocalDateTime projectionTime;

    public Projection() {}

    public Projection(Long id, Long roomId, Long movieId, LocalDateTime projectionTime)
    {
        this.id = id;
        this.roomId = roomId;
        this.movieId = movieId;
        this.projectionTime = projectionTime;
    }

    public Long getId()
    {
        return id;
    }

    public Long getRoomId()
    {
        return roomId;
    }

    public Long getMovieId()
    {
        return movieId;
    }

    public LocalDateTime getProjectionTime()
    {
        return projectionTime;
    }
}
