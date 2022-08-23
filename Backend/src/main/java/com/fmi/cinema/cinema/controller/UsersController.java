package com.fmi.cinema.cinema.controller;

import com.fmi.cinema.cinema.model.dto.usersDTO.LoginRequestDTO;
import com.fmi.cinema.cinema.model.dto.usersDTO.LoginResponseDTO;
import com.fmi.cinema.cinema.model.dto.usersDTO.RegisterRequestUserDTO;
import com.fmi.cinema.cinema.model.dto.usersDTO.RegisterResponseUserDTO;
import com.fmi.cinema.cinema.service.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody final LoginRequestDTO loginRequest,
                                  final HttpSession session)
    {
        return usersService.login(loginRequest, session);
    }

    @GetMapping("/info")
    public void getUserInfo(final HttpSession httpSession)
    {
        usersService.getUserInfo(httpSession);
    }
}
