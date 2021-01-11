package com.grouptwo.legisrate.data;

import com.grouptwo.legisrate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
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

    /**
     * Constructs the UserDaoDB class
     * @param jdbcTemplate the JDBC Template
     */
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
        final String sql = "INSERT INTO `Users`(`Username`, `State`/*, `Email`, `Password`*/) VALUES(?, ?/*, ?, ?*/);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getState());
//            statement.setString(3, user.getEmail());
//            statement.setString(4, user.getPassword());
            return statement;
        }, keyHolder);
        user.setUserID(keyHolder.getKey().intValue());
        return user;
    }

    /**
     * Gets a list of all users from the `Users` table in the database
     * @return a list of all users
     */
    @Override
    public List<User> getAllUsers() {
        final String sql = "SELECT `UserId`, `Username`, `State`/*, `Email`, `Password`*/ FROM `Users`;";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    /**
     * Gets a specified user from the `Users` table in the database
     * @param userID the ID of the specified user
     * @return the specified user
     */
    @Override
    public User getUser(int userID) {
        try {
            final String sql = "SELECT `UserId`, `Username`, `State`/*, `Email`, `Password`*/ FROM `Users` WHERE `UserId` = ?;";
            return jdbcTemplate.queryForObject(sql, new UserMapper(), userID);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * Updates a specified user in the `Users` table in the database
     * @param user the specified user
     * @return true if the specified user exists and is updated
     */
    @Override
    public boolean update(User user) {
        final String sql = "UPDATE `Users` SET `Username` = ?, `State` = ?/*, `Email` = ?, `Password` = ?*/ WHERE `UserId` = ?;";
        return jdbcTemplate.update(sql, user.getUsername(), user.getState(),/* user.getEmail(), user.getPassword(),*/ user.getUserID()) > 0;
    }

    /**
     * Deletes a specified user from the `Users` table in the database
     * @param userID the ID of the specified user
     * @return true if the specified user exists and is deleted
     */
    @Override
    public boolean delete(int userID) {
        final String sql = "DELETE FROM `Users` WHERE `UserId` = ?;";
        return jdbcTemplate.update(sql, userID) > 0;
    }

    /**
     * The user mapper class
     */
    private static final class UserMapper implements RowMapper<User> {

        /**
         * Maps database rows to User objects
         * @param rs the result set
         * @param index the index of the current row
         * @return the User object
         * @throws SQLException an SQL exception
         */
        @Override
        public User mapRow(ResultSet rs, int index) throws SQLException {
            User user = new User();
            user.setUserID(rs.getInt("UserId"));
            user.setUsername(rs.getString("Username"));
            user.setState(rs.getString("State"));
//            user.setEmail(rs.getString("Email"));
//            user.setPassword(rs.getString("Password"));
            return user;
        }

    }

}
