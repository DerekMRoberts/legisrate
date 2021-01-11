package com.grouptwo.legisrate.exception;

/**
 * LegisRate
 * Final Group Project
 * C65 Java Full-Stack with React
 * The Software Guild
 *
 * @author Russell Taylor, Rosalinda Powell, Derek Roberts, John Michael Rondello, Abdulrasaq Saliu
 * Date: January 15, 2021
 *
 * InvalidUserException
 * A custom exception class for invalid user http post requests
 */
public class InvalidUserException extends Exception {

    /**
     * Calls the super class
     * @param ex the exception
     */
    public InvalidUserException(String ex) {
        super(ex);
    }

}