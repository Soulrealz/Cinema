package com.fmi.cinema.cinema.exceptions;

public class MovieAlreadyExistsException extends RuntimeException {
    public MovieAlreadyExistsException(final String message) {
        super(message);
    }
}