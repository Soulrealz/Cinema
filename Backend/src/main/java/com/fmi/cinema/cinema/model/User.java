package com.fmi.cinema.cinema.model;

import com.fmi.cinema.cinema.model.dto.usersDTO.RegisterRequestUserDTO;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User
{
    @Id
    private long id;
    private final String first_name;
    private final String last_name;
    private final String email;
    private final String password;
    public User(RegisterRequestUserDTO user, String hashedPassword)
    {
        first_name = user.first_name();
        last_name = user.last_name();
        email = user.email();
        password = hashedPassword;
    }

    public long getId() { return id; }
    public String getFirst_name() { return first_name; }
    public String getLast_name() { return last_name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
