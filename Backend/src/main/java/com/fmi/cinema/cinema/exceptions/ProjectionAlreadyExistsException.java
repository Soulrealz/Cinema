package com.fmi.cinema.cinema.exceptions;

public class ProjectionAlreadyExistsException extends RuntimeException {
    public ProjectionAlreadyExistsException(final String message) {
        super(message);
    }
}
