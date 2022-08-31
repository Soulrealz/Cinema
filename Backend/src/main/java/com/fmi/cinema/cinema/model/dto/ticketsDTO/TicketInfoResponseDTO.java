package com.fmi.cinema.cinema.model.dto.ticketsDTO;

import java.time.LocalDateTime;

public record TicketInfoResponseDTO (String userFirstName,
									 String movieName,
									 LocalDateTime boughtOn,
									 Long seatId,
									 Long projectionId){}
