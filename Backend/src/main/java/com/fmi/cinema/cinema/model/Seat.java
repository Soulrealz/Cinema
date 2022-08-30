package com.fmi.cinema.cinema.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatId;

    @ManyToOne
    @JoinColumn(name="id")
    private Room room;

    public Seat() {}

    public Seat(Long seatId, Room room) {
        this.seatId = seatId;
        this.room = room;
    }

    public Long getSeatId() {
        return seatId;
    }

    public Room getRoom() {
        return room;
    }
}
