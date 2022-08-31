package com.fmi.cinema.cinema.service;

import com.fmi.cinema.cinema.model.Ticket;
import com.fmi.cinema.cinema.model.User;
import com.fmi.cinema.cinema.model.dto.ticketsDTO.TicketInfoResponseDTO;
import com.fmi.cinema.cinema.repository.TicketRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService
{

	private final TicketRepository ticketRepository;
	private final UsersService usersService;
	private final SessionManager sessionManager;

	public TicketService(final TicketRepository tr, final UsersService us, final SessionManager sm)
	{
		ticketRepository = tr;
		usersService = us;
		sessionManager = sm;
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

	public TicketInfoResponseDTO createNewTicket(final HttpSession session)
	{//give seat id and room id
		User user = usersService.getSessionUser(session);
		//Ticket tkt = new Ticket(user, )
		return null;
	}
}