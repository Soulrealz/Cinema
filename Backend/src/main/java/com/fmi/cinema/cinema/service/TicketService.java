package com.fmi.cinema.cinema.service;

import com.fmi.cinema.cinema.model.Ticket;
import com.fmi.cinema.cinema.model.User;
import com.fmi.cinema.cinema.model.dto.ticketsDTO.TicketInfoResponseDTO;
import com.fmi.cinema.cinema.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService
{

	private final TicketRepository ticketRepository;

	public TicketService(final TicketRepository tr)
	{
		ticketRepository = tr;
	}

	public List<TicketInfoResponseDTO> getUserTicketsInfo(final User user)
	{
		final List<Ticket> boughtTickets = ticketRepository.findByUser(user);

		return getTicketsInfo(boughtTickets);
	}

	private static List<TicketInfoResponseDTO> getTicketsInfo(final List<Ticket> tickets)
	{
		return tickets.stream()
					  .map(TicketService::getTicketInfo)
					  .collect(Collectors.toList());
	}

	private static TicketInfoResponseDTO getTicketInfo(final Ticket ticket)
	{
		return null;
	}
}