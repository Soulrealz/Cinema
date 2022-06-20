package com.fmi.cinema.cinema.model;

import com.fmi.cinema.cinema.model.dto.usersDTO.RegisterRequestUserDTO;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User
{
    @Id
    private long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    public User(RegisterRequestUserDTO user, String hashedPassword)
    {
        firstName = user.firstName();
        lastName = user.lastName();
        email = user.email();
        password = hashedPassword;
    }

    public long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
