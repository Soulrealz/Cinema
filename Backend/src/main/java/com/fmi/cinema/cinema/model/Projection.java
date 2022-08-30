package com.fmi.cinema.cinema.model;

import javax.persistence.*;
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
        this.projectionTime = projection_time; //1-1, 1-2, 2-3, 2-4
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
