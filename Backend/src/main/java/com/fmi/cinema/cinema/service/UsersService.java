package com.fmi.cinema.cinema.service;

import com.fmi.cinema.cinema.exceptions.BadRequestException;
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
    private final SessionManager sessionManager;

    private final PasswordEncoder encoder;

    public UsersService(final UsersRepository usersRepository,
                        final SessionManager sessionManager)
    {
        this.usersRepository = usersRepository;
        this.sessionManager = sessionManager;
        this.encoder = new BCryptPasswordEncoder();
    }

    public RegisterResponseUserDTO register(RegisterRequestUserDTO userDTO)
    {
        if (usersRepository.findByEmail(userDTO.email()).isPresent())
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
         validateSessionOnLogin(session);

        final User user = usersRepository.findByEmail(loginRequestDTO.email())
                                                   .orElseThrow(() -> new BadRequestException("User with this email already exists."));

        final boolean isLoginSuccessful = isLoginSuccessful(loginRequestDTO.password(), user.getPassword());
        sessionManager.createSession(session, user.getId());

        return new LoginResponseDTO(loginRequestDTO.email(), isLoginSuccessful);
    }

    public void logout(final HttpSession session)
    {
        sessionManager.logout(session);
    }

    public UserInfoResponseDTO getUserInfo(final HttpSession session)
    {
        final long userId = sessionManager.getUserIdFromSession(session);

        final User user = usersRepository.getById(userId);
        final List<TicketInfoResponseDTO> userTickets = null;

        return buildUserInfo(user, userTickets);
    }

    public void validateSessionOnLogin(final HttpSession session)
    {
        if (sessionManager.checkIfThereIsLoggedUser(session).isEmpty())
        {
            return;
        }

        throw new BadRequestException("User already logged in.");
    }

    public User getSessionUser(HttpSession session)
    {
        validateSessionOnLogin(session);
        return usersRepository.getById(sessionManager.getUserIdFromSession(session));
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
