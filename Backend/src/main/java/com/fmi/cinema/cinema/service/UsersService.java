package com.fmi.cinema.cinema.service;

import com.fmi.cinema.cinema.exceptions.BadRequestException;
import com.fmi.cinema.cinema.model.Ticket;
import com.fmi.cinema.cinema.model.User;
import com.fmi.cinema.cinema.model.dto.ticketsDTO.TicketInfoResponseDTO;
import com.fmi.cinema.cinema.model.dto.usersDTO.LoginRequestDTO;
import com.fmi.cinema.cinema.model.dto.usersDTO.LoginResponseDTO;
import com.fmi.cinema.cinema.model.dto.usersDTO.RegisterRequestUserDTO;
import com.fmi.cinema.cinema.model.dto.usersDTO.RegisterResponseUserDTO;
import com.fmi.cinema.cinema.model.dto.usersDTO.UserInfoResponseDTO;
import com.fmi.cinema.cinema.repository.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UsersService
{
    private final UsersRepository usersRepository;

    private final TicketService ticketService;

    private final SessionManager sessionManager;

    private final PasswordEncoder encoder;

    public UsersService(final UsersRepository usersRepository,
                        final TicketService ticketService,
                        final SessionManager sessionManager)
    {
        this.usersRepository = usersRepository;
        this.ticketService = ticketService;
        this.sessionManager = sessionManager;
        this.encoder = new BCryptPasswordEncoder();
    }

    public RegisterResponseUserDTO register(RegisterRequestUserDTO userDTO)
    {
        if (usersRepository.findByEmail(userDTO.email()).isEmpty())
        {
            throw new BadRequestException("User with this email already exists.");
        }

        userDTO.validateUserInformation();

        final String pass = encoder.encode(userDTO.password());

        User user = new User(userDTO, pass);
        user = usersRepository.save(user);

        return new RegisterResponseUserDTO(user);
    }

    public LoginResponseDTO login(final LoginRequestDTO loginRequestDTO,
                                  final HttpSession session)
    {
         validateSession(session);

        final User user = usersRepository.findByEmail(loginRequestDTO.email())
                                                   .orElseThrow(() -> new BadRequestException("User with this email already exists."));

        final boolean isLoginSuccessful = isLoginSuccessful(loginRequestDTO.password(), user.getPassword());
        sessionManager.createSession(session, user.getId());

        return new LoginResponseDTO(loginRequestDTO.email(), isLoginSuccessful);
    }

    public UserInfoResponseDTO getUserInfo(final HttpSession session)
    {
        final long userId = sessionManager.getUserIdFromSession(session);

        final User user = usersRepository.getById(userId);
        final List<TicketInfoResponseDTO> userTickets = ticketService.getUserTicketsInfo(user);

        return buildUserInfo(user, userTickets);
    }

    private void validateSession(final HttpSession session)
    {
        if (sessionManager.checkIfThereIsLoggedUser(session).isEmpty())
        {
            return;
        }

        throw new BadRequestException("User already logged in.");
    }

    private boolean isLoginSuccessful(final String requestPassword,
                                            final String password)
    {
        return encoder.matches(requestPassword, password);
    }

    private UserInfoResponseDTO buildUserInfo(final User user,
                                              final List<TicketInfoResponseDTO> tickets)
    {
        return new UserInfoResponseDTO(user.getFirstName(),
                                       user.getLastName(),
                                       user.getEmail(),
                                       tickets);
    }


}
