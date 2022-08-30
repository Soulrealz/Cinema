package com.fmi.cinema.cinema.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy="room")
    private List<Seat> seats;


    public Room() {}

    public Room(Long id, List<Seat> seats) {
        this.id = id;
        this.seats = seats;
    }

    public Long getId() {
        return id;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}