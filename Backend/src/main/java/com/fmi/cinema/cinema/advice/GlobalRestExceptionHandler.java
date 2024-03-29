package com.fmi.cinema.cinema.advice;

import com.fmi.cinema.cinema.exceptions.BadRequestException;
import com.fmi.cinema.cinema.exceptions.MovieAlreadyExistsException;
import com.fmi.cinema.cinema.exceptions.ProjectionAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String ERROR_MESSAGE = "{ \"error\": \"%s\" }";

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleElementNotFound(NoSuchElementException e) {
        final String responseBody = String.format(ERROR_MESSAGE, e.getMessage());
        return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MovieAlreadyExistsException.class)
    public ResponseEntity<Object> handleMovieAlreadyExists(MovieAlreadyExistsException e) {
        final String responseBody = String.format(ERROR_MESSAGE, e.getMessage());
        return new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProjectionAlreadyExistsException.class)
    public ResponseEntity<Object> handleProjectionAlreadyExists(ProjectionAlreadyExistsException e) {
        final String responseBody = String.format(ERROR_MESSAGE, e.getMessage());
        return new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestException e) {
        final String responseBody = String.format(ERROR_MESSAGE, e.getMessage());
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException e,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        final String responseBody = String.format(ERROR_MESSAGE, e.getMessage());
        return new ResponseEntity<>(responseBody, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        final Map<String, String> errors = new HashMap<>();
        e.getBindingResult()
                .getAllErrors()
                .forEach((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(fieldName, message);
                });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}