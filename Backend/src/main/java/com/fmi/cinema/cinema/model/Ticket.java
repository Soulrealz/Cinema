package com.fmi.cinema.cinema.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column
    private int rowNumber;

    @Column
    private int seatNumber;

    @CreationTimestamp
    private LocalDateTime boughtOn;

    public Ticket() {}

    public Ticket(User user, Movie movie, int rowNumber, int seatNumber, LocalDateTime boughtOn) {
        this.user = user;
        this.movie = movie;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.boughtOn = boughtOn;
    }
    public Long getTicketId() {
        return ticketId;
    }

    public User getUser() {
        return user;
    }

    public Movie getMovie() {
        return movie;
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