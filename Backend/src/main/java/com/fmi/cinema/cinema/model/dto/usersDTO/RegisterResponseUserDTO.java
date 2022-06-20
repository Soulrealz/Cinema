package com.fmi.cinema.cinema.model.dto.usersDTO;

import com.fmi.cinema.cinema.model.User;
import org.springframework.stereotype.Component;

@Component
public class RegisterResponseUserDTO
{
    private final long id;
    private final String first_name;
    private final String last_name;
    private final String email;


    public RegisterResponseUserDTO(final User user)
    {
        id = user.getId();
        first_name = user.getFirst_name();
        last_name = user.getLast_name();
        email = user.getEmail();
    }

    public long getId() { return id; }
    public String getFirst_name() { return first_name; }
    public String getLast_name() { return last_name; }
    public String getEmail() { return email; }
}
