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
 * InvalidReviewException
 * A custom exception class for invalid review http post requests
 */
public class InvalidReviewException extends Exception {

    /**
     * Calls the super class
     * @param ex the exception
     */
    public InvalidReviewException(String ex) {
        super(ex);
    }

}