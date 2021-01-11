package com.grouptwo.legisrate.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;

/**
 * LegisRate
 * Final Group Project
 * C65 Java Full-Stack with React
 * The Software Guild
 *
 * @author Russell Taylor, Rosalinda Powell, Derek Roberts, John Michael Rondello, Abdulrasaq Saliu
 * Date: January 15, 2021
 *
 * LegislationControllerExceptionHandler
 * Handles exceptions for the LegislationController class
 */
@ControllerAdvice
@RestController
public class LegislationControllerExceptionHandler extends Exception {

    /**
     * Handles Invalid Legislation Exceptions
     * @param ex the exception
     * @param request the request
     * @return the http 422 Unprocessable Entity status code
     */
    @ExceptionHandler(InvalidLegislationException.class)
    public final ResponseEntity<Error> handleInvalidLegislationException(InvalidLegislationException ex, WebRequest request) {
        Error err = new Error();
        err.setMessage("Could not create or update legislation. The required request body is missing or invalid. Please ensure the request is valid and try again.");
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Handles Invalid Review Exceptions
     * @param ex the exception
     * @param request the request
     * @return the http 422 Unprocessable Entity status code
     */
    @ExceptionHandler(InvalidReviewException.class)
    public final ResponseEntity<Error> handleInvalidReviewException(InvalidReviewException ex, WebRequest request) {
        Error err = new Error();
        err.setMessage("Could not create or update review. The required request body is missing or invalid. Please ensure the request is valid and try again.");
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Handles Invalid User Exceptions
     * @param ex the exception
     * @param request the request
     * @return the http 422 Unprocessable Entity status code
     */
    @ExceptionHandler(InvalidUserException.class)
    public final ResponseEntity<Error> handleInvalidUserException(InvalidUserException ex, WebRequest request) {
        Error err = new Error();
        err.setMessage("Could not create or update user. The required request body is missing or invalid. Please ensure the request is valid and try again.");
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Handles Http Message Not Readable Exceptions
     * @param ex the exception
     * @param request the request
     * @return the http 400 Bad Request status code
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<Error> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        Error err = new Error();
        err.setMessage("The required request body is missing or invalid. Please ensure the request is valid and try again.");
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles Http Request Method Not Supported Exceptions
     * @param ex the exception
     * @param request the request
     * @return the http 400 Bad Request status code
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, MethodArgumentTypeMismatchException.class})
    public final ResponseEntity<Error> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, WebRequest request) {
        Error err = new Error();
        err.setMessage("The HTTP request method and URL do not exist. Please ensure the request is valid and try again.");
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles SQL Exceptions
     * @param ex the exception
     * @param request the request
     * @return the http 422 Unprocessable Entity status code
     */
    @ExceptionHandler({SQLException.class, DataIntegrityViolationException.class, SQLSyntaxErrorException.class, BadSqlGrammarException.class})
    public final ResponseEntity<Error> handleSQLException(SQLException ex, WebRequest request) {
        Error err = new Error();
        err.setMessage("There was an error in the database. Please ensure the request is valid and try again.");
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * Handles Exceptions
     * @param ex the exception
     * @param request the request
     * @return the http 422 Unprocessable Entity status code
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Error> handleException(Exception ex, WebRequest request) {
        Error err = new Error();
        err.setMessage("There was an error. Please ensure the request is valid and try again.");
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
