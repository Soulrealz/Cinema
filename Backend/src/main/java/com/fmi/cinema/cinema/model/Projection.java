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

    public Projection(Long id, Long room_id, Long movie_id, LocalDateTime projection_time)
    {
        this.id = id;
        this.roomId = room_id;
        this.movieId = movie_id;
        this.projectionTime = projection_time;
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

    public LocalDateTime projectionTime()
    {
        return projectionTime;
    }
}
