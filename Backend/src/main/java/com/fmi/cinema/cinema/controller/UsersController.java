package com.fmi.cinema.cinema.controller;

import com.fmi.cinema.cinema.model.dto.usersDTO.RegisterRequestUserDTO;
import com.fmi.cinema.cinema.model.dto.usersDTO.RegisterResponseUserDTO;
import com.fmi.cinema.cinema.service.UsersService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController
{
    private final UsersService usersService;

    public UsersController(UsersService service)
    {
        usersService = service;
    }

    @PutMapping("/register")
    public RegisterResponseUserDTO register(@RequestBody RegisterRequestUserDTO userDTO)
    {
        return usersService.register(userDTO);
    }
}
