package com.fmi.cinema.cinema.exceptions;

public class NoMoviesException extends RuntimeException {

    public NoMoviesException(final String message)
    {
        super(message);
    }
}
