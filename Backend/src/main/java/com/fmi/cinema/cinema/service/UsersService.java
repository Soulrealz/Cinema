package com.fmi.cinema.cinema.service;

import com.fmi.cinema.cinema.exceptions.BadRequestException;
import com.fmi.cinema.cinema.model.User;
import com.fmi.cinema.cinema.model.dto.usersDTO.RegisterRequestUserDTO;
import com.fmi.cinema.cinema.model.dto.usersDTO.RegisterResponseUserDTO;
import com.fmi.cinema.cinema.repository.UsersRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService
{
    private final UsersRepository usersRepository;

    public UsersService(final UsersRepository users)
    {
        usersRepository = users;
    }

    public RegisterResponseUserDTO register(RegisterRequestUserDTO userDTO)
    {
        if (usersRepository.findByEmail(userDTO.email()).isPresent())
        {
            throw new BadRequestException("User with this email already exists.");
        }

        userDTO.validateUserInformation();

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        final String pass = encoder.encode(userDTO.password());

        User user = new User(userDTO, pass);
        user = usersRepository.save(user);

        return new RegisterResponseUserDTO(user);
    }

}
