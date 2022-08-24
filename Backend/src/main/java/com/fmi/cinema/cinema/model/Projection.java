package com.fmi.cinema.cinema.model;

import javax.persistence.*;

@Entity
@Table(name = "projections")
public class Projection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long room_id;

    @Column
    private Long movie_id;

    public Projection() {}

    public Projection(Long id, Long room_id, Long movie_id)
    {
        this.id = id;
        this.room_id = room_id;
        this.movie_id = movie_id;
    }

    public Long getId()
    {
        return id;
    }

    public Long getRoom_id()
    {
        return room_id;
    }

    public Long getMovie_id()
    {
        return movie_id;
    }
}
