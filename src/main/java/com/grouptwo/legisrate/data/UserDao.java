package com.grouptwo.legisrate.data;

import com.grouptwo.legisrate.model.User;

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
 * UserDao
 * The user data-access-object interface
 */
public interface UserDao {

    /**
     * Adds a new user to the `Users` table in the database
     * @param user the new user
     * @return the new user updated with an auto-generated userID
     */
    User add(User user);

    /**
     * Gets a list of all users from the `Users` table in the database
     * @return a list of all users
     */
    List<User> getAllUsers();

    /**
     * Gets a specified user from the `Users` table in the database
     * @param userID the ID of the specified user
     * @return the specified user
     */
    User getUser(int userID);

    /**
     * Updates a specified user in the `Users` table in the database
     * @param user the specified user
     * @return true if the specified user exists and is updated
     */
    boolean update(User user);

    /**
     * Deletes a specified user from the `Users` table in the database
     * @param userID the ID of the specified user
     * @return true if the specified user exists and is deleted
     */
    boolean delete(int userID);

}
