package com.fmi.cinema.cinema.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketId;

    @Column
    private Long userId;

    @Column
    private Long movieId;

    @Column
    private Long roomId;

    @Column
    private int rowNumber;

    @Column
    private int seatNumber;

    @Column
    private LocalDateTime boughtOn;

    public Ticket() {
    }

    public Ticket(Long ticketId, Long userId, Long movieId, Long roomId, int rowNumber, int seatNumber, LocalDateTime boughtOn) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.movieId = movieId;
        this.roomId = roomId;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.boughtOn = boughtOn;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public LocalDateTime getBoughtOn() {
        return boughtOn;
    }
}