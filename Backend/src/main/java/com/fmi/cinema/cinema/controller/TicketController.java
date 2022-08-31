package com.fmi.cinema.cinema.controller;

import com.fmi.cinema.cinema.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/tickets")
public class TicketController
{
    //TODO:
    // ticket cleanup for old tickets

    private final TicketService ticketService;

    @Autowired
    public TicketController(final TicketService ts)
    {
        ticketService = ts;
    }
    //get one ticket by id

}