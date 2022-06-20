package com.fmi.cinema.cinema.model.dto.usersDTO;

import com.fmi.cinema.cinema.exceptions.BadRequestException;
import com.fmi.cinema.cinema.model.utils.UserValidator;
import org.springframework.stereotype.Component;

@Component
public record RegisterRequestUserDTO (String first_name, String last_name, String email, String password, String confirmPassword)
{
    public void validateUserInformation()
    {
        if(!UserValidator.validateName(first_name))
        {
            throw new BadRequestException("You have entered invalid first name.");
        }
        if(!UserValidator.validateName(last_name))
        {
            throw new BadRequestException("You have entered invalid last name.");
        }
        if(!UserValidator.validateEmail(email))
        {
            throw new BadRequestException("You have entered invalid email.");
        }
        if(!UserValidator.validatePassword(password))
        {
            throw new BadRequestException("You have entered too weak password.");
        }
        if(!UserValidator.validatePasswordConfirmation(password, confirmPassword))
        {
            throw new BadRequestException("Passwords do not match.");
        }
    }
}
