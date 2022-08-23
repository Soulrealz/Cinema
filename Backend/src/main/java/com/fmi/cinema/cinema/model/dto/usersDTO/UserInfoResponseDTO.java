package com.fmi.cinema.cinema.model.dto.usersDTO;

import com.fmi.cinema.cinema.model.dto.ticketsDTO.TicketInfoResponseDTO;

import java.util.List;

public record UserInfoResponseDTO (String firstName,
								   String lastName,
								   String email,
								   List<TicketInfoResponseDTO> boughtTickets){}
