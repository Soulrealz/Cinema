package com.fmi.cinema.cinema.model;

import com.fmi.cinema.cinema.model.dto.usersDTO.RegisterRequestUserDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User() {}

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