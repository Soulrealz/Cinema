package com.fmi.cinema.cinema.controller;

import com.fmi.cinema.cinema.model.dto.ticketsDTO.TicketInfoResponseDTO;
import com.fmi.cinema.cinema.model.dto.usersDTO.LoginRequestDTO;
import com.fmi.cinema.cinema.model.dto.usersDTO.LoginResponseDTO;
import com.fmi.cinema.cinema.model.dto.usersDTO.RegisterRequestUserDTO;
import com.fmi.cinema.cinema.model.dto.usersDTO.RegisterResponseUserDTO;
import com.fmi.cinema.cinema.service.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UsersController
{
    private final UsersService usersService;

    public UsersController(UsersService service)
    {
        usersService = service;
    }

    @PostMapping("/register")
    public RegisterResponseUserDTO register(@RequestBody RegisterRequestUserDTO userDTO)
    {
        return usersService.register(userDTO);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody final LoginRequestDTO loginRequest,
                                  final HttpSession session)
    {
        return usersService.login(loginRequest, session);
    }

    @PostMapping("/logout")
    public void logout(final HttpSession session)
    {
        usersService.logout(session);
    }

    @GetMapping("/info")
    public void getUserInfo(final HttpSession httpSession)
    {
        usersService.getUserInfo(httpSession);
    }

    @PostMapping("/newticket/{seatId}/{projectionId}")
    public TicketInfoResponseDTO createNewTicket(final HttpSession session, final @PathVariable long seatId, final @PathVariable long projectionId)
    {
        return usersService.createNewTicket(session, seatId, projectionId);
    }

    @GetMapping("/getTicket/{ticketId}")
    public Optional<TicketInfoResponseDTO> getTicket(final @PathVariable long ticketId)
    {
        return usersService.getUserTicket(ticketId);
    }

}
