package com.fmi.cinema.cinema.model;

import javax.persistence.Column;
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

    @Column
    private Long capacity;

    @OneToMany(mappedBy="room")
    private List<Seat> seats;


    public Room() {}

    public Room(Long id, Long capacity, List<Seat> seats) {
        this.id = id;
        this.capacity = capacity;
        this.seats = seats;
    }

    public Long getId() {
        return id;
    }

    public Long getCapacity() {
        return capacity;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}