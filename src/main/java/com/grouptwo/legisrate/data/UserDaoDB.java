package com.sg.data;

import com.sg.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * LegisRate
 * Final Group Project
 * C65 Java Full-Stack with React
 * The Software Guild
 *
 * @author Russell Taylor, Rosalinda Powell, Derek Roberts, John Michael Rondello, Abdulrasaq Saliu
 * Date: January 15, 2021
 *
 * UserDaoDB
 * The user data-access-object interface
 */
public class UserDaoDB {

    /**
     * Adds a new user to the `Users` table in the database
     * @param user the new user
     * @return the new user updated with an auto-generated userID
     */
    public User add(User user) {
        return new User();
    }

    /**
     * Gets a list of all users from the `Users` table in the database
     * @return a list of all users
     */
    public List<User> getAllUsers() {
        return new ArrayList<>();
    }

    /**
     * Gets a specified user from the `Users` table in the database
     * @param userID the ID of the specified user
     * @return the specified user
     */
    public User getUser(int userID) {
        return new User();
    }

    /**
     * Updates a specified user in the `Users` table in the database
     * @param user the specified user
     * @return true if the specified user exists and is updated
     */
    public boolean update(User user) {
        return true;
    }

    /**
     * Deletes a specified user from the `Users` table in the database
     * @param userID the ID of the specified user
     * @return true if the specified user exists and is deleted
     */
    public boolean delete(int userID) {
        return true;
    }

}