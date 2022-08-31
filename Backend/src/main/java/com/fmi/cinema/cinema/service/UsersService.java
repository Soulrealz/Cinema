package com.fmi.cinema.cinema.service;

import com.fmi.cinema.cinema.exceptions.BadRequestException;
import com.fmi.cinema.cinema.model.Projection;
import com.fmi.cinema.cinema.model.Seat;
import com.fmi.cinema.cinema.model.Ticket;
import com.fmi.cinema.cinema.model.User;
import com.fmi.cinema.cinema.model.dto.ticketsDTO.TicketInfoResponseDTO;
import com.fmi.cinema.cinema.model.dto.usersDTO.LoginRequestDTO;
import com.fmi.cinema.cinema.model.dto.usersDTO.LoginResponseDTO;
import com.fmi.cinema.cinema.model.dto.usersDTO.RegisterRequestUserDTO;
import com.fmi.cinema.cinema.model.dto.usersDTO.RegisterResponseUserDTO;
import com.fmi.cinema.cinema.model.dto.usersDTO.UserInfoResponseDTO;
import com.fmi.cinema.cinema.repository.MovieRepository;
import com.fmi.cinema.cinema.repository.ProjectionRepository;
import com.fmi.cinema.cinema.repository.RoomRepository;
import com.fmi.cinema.cinema.repository.SeatRepository;
import com.fmi.cinema.cinema.repository.TicketRepository;
import com.fmi.cinema.cinema.repository.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService
{
    private final UsersRepository usersRepository;
    private final TicketRepository ticketRepository;
    private final ProjectionRepository projectionRepository;
    private final SeatRepository seatRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final SessionManager sessionManager;

    private final TicketService ticketService;

    private final PasswordEncoder encoder;

    public UsersService(final UsersRepository usersRepository,
                        final SessionManager sessionManager,
                        final TicketService ts,
                        final TicketRepository tr,
                        final ProjectionRepository pr,
                        final SeatRepository sr,
                        final MovieRepository mr,
                        final RoomRepository rr)
    {
        this.usersRepository = usersRepository;
        this.sessionManager = sessionManager;
        ticketService = ts;
        ticketRepository = tr;
        projectionRepository = pr;
        seatRepository = sr;
        movieRepository = mr;
        roomRepository = rr;
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
         validateSession(session);

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
        final List<TicketInfoResponseDTO> userTickets = ticketService.getUserTicketsInfo(user);

        return buildUserInfo(user, userTickets);
    }

    public void validateSession(final HttpSession session)
    {
        if (sessionManager.checkIfThereIsLoggedUser(session).isEmpty())
        {
            return;
        }

        throw new BadRequestException("User already logged in.");
    }

    public User getSessionUser(HttpSession session)
    {
        validateSession(session);
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

    public TicketInfoResponseDTO createNewTicket(final HttpSession session, long seatId, long projectionId)
    {
        User user = getSessionUser(session);
        Optional<Seat> seat = seatRepository.findById(seatId);
        Optional<Projection> projection = projectionRepository.findById(projectionId);
        if (seat.isPresent() && projection.isPresent())
        {
            Seat realSeat = seat.get();
            Projection realProjection = projection.get();

            Ticket tkt = new Ticket(user, realSeat, realProjection, LocalDateTime.now());
            ticketRepository.save(tkt);

            // no need to check if movie is present since we know projection is legit
            return new TicketInfoResponseDTO(user.getFirstName(),
                    movieRepository.findById(realProjection.getMovieId()).get().getName(),
                    LocalDateTime.now(),
                    realSeat.getSeatId(),
                    realProjection.getId());
        }

        throw new BadRequestException("Invalid IDs.");
    }

    public Optional<TicketInfoResponseDTO> getUserTicket(final long ticketId)
    {
        Optional<Ticket> tkt = ticketRepository.findById(ticketId);
        if (tkt.isEmpty())
        {
            return Optional.empty();
        }
        Ticket ticket = tkt.get();
        Projection proj = ticket.getProjection();
        return Optional.of(new TicketInfoResponseDTO(ticket.getUser().getFirstName(),
                movieRepository.findById(proj.getMovieId()).get().getName(),
                ticket.getBoughtOn(),
                ticket.getSeat().getSeatId(),
                proj.getId()));
    }


}
