package com.fmi.cinema.cinema.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

    @OneToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "projection_id")
    private Projection projection;

    @CreationTimestamp
    private LocalDateTime boughtOn;

    public Ticket() {}

    public Ticket(User user, Seat seat, Projection projection, LocalDateTime boughtOn) {
        this.user = user;
        this.seat = seat;
        this.projection = projection;
        this.boughtOn = boughtOn;
    }
    public Long getTicketId() {
        return ticketId;
    }

    public User getUser() {
        return user;
    }

    public Projection getProjection() {
        return projection;
    }

    public LocalDateTime getBoughtOn() {
        return boughtOn;
    }
}