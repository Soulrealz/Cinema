package com.fmi.cinema.cinema.model.dto.usersDTO;

import com.fmi.cinema.cinema.model.User;
import org.springframework.stereotype.Component;

@Component
public class RegisterResponseUserDTO
{
    private final long id;
    private final String firstName;
    private final String lastName;
    private final String email;


    public RegisterResponseUserDTO(final User user)
    {
        id = user.getId();
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
    }

    public long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
}
