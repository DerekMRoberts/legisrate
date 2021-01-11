package com.grouptwo.legisrate.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * LegisRate
 * Final Group Project
 * C65 Java Full-Stack with React
 * The Software Guild
 *
 * @author Russell Taylor, Rosalinda Powell, Derek Roberts, John Michael Rondello, Abdulrasaq Saliu
 * Date: January 15, 2021
 *
 * User
 * A model for User objects
 */
public class User {

    private int userID;
    @NotBlank(message = "Username must not be empty.")
    @Size(max = 30, message="Username must be fewer than 30 characters.")
    private String username;
    @Size(min = 2, max = 2, message="State must be 2 characters.")
    private String state;
    private String email; // add?
    private String password; // add?

    /**
     * Gets the user ID
     * @return the user ID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Sets the user ID
     * @param userID the user ID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Gets the username
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's state
     * @return the user's state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the user's state
     * @param state the user's state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the user's email address
     * @return the user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address
     * @param email the user's email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's password
     * @return the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password
     * @param password the user's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Determines whether two User objects are equal
     * @param o the object to be compared with the current object
     * @return a boolean indicating whether the two objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID == user.userID && Objects.equals(username, user.username) && Objects.equals(state, user.state) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    /**
     * Overrides the hashCode() method
     * @return the object as a hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(userID, username, state, email, password);
    }

    /**
     * Overrides the toString() method
     * @return the object as a String
     */
    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", state='" + state + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
