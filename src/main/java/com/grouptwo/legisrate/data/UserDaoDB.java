package com.grouptwo.legisrate.data;

import com.grouptwo.legisrate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
@Repository
@Profile("database")
public class UserDaoDB implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Adds a new user to the `Users` table in the database
     * @param user the new user
     * @return the new user updated with an auto-generated userID
     */
    @Override
    public User add(User user) {
        return new User();
    }

    /**
     * Gets a list of all users from the `Users` table in the database
     * @return a list of all users
     */
    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>();
    }

    /**
     * Gets a specified user from the `Users` table in the database
     * @param userID the ID of the specified user
     * @return the specified user
     */
    @Override
    public User getUser(int userID) {
        return new User();
    }

    /**
     * Updates a specified user in the `Users` table in the database
     * @param user the specified user
     * @return true if the specified user exists and is updated
     */
    @Override
    public boolean update(User user) {
        return true;
    }

    /**
     * Deletes a specified user from the `Users` table in the database
     * @param userID the ID of the specified user
     * @return true if the specified user exists and is deleted
     */
    @Override
    public boolean delete(int userID) {
        return true;
    }

}