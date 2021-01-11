package com.grouptwo.legisrate.exception;

import java.time.LocalDateTime;

/**
 * LegisRate
 * Final Group Project
 * C65 Java Full-Stack with React
 * The Software Guild
 *
 * @author Russell Taylor, Rosalinda Powell, Derek Roberts, John Michael Rondello, Abdulrasaq Saliu
 * Date: January 15, 2021
 *
 * Error
 * A model for Error objects
 */
public class Error {

    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;

    /**
     * Gets a current timestamp
     * @return the current timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets a current timestamp
     * @param timestamp the current timestamp
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets an error message
     * @return the error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets an error message
     * @param message the error message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}