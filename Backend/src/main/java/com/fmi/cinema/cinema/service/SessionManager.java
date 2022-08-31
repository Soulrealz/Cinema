package com.fmi.cinema.cinema.service;

import com.fmi.cinema.cinema.exceptions.BadRequestException;
import com.fmi.cinema.cinema.model.User;
import com.fmi.cinema.cinema.repository.UsersRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class SessionManager
{

	private final static String X_CINEMA_USER = "X_CINEMA_USER";

	private final UsersRepository usersRepository;

	public SessionManager(final UsersRepository usersRepository)
	{
		this.usersRepository = usersRepository;
	}

	public void createSession(final HttpSession session, final long id)
	{
		session.setAttribute(X_CINEMA_USER, id);
	}

	public Optional<User> checkIfThereIsLoggedUser(final HttpSession session)
	{
		if (session.getAttribute(X_CINEMA_USER) == null)
		{
			return Optional.empty();
		}

		final long userId = (long) session.getAttribute(X_CINEMA_USER);

		return Optional.of(usersRepository.findById(userId)
										  .orElseThrow(() -> new BadRequestException("User not found")));
	}

	public long getUserIdFromSession(final HttpSession session)
	{
		return (long) session.getAttribute(X_CINEMA_USER);
	}

	public void logout(final HttpSession session)
	{
		session.invalidate();
	}
}
