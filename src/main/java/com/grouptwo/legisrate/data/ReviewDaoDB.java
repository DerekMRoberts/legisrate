package com.grouptwo.legisrate.data;

import com.grouptwo.legisrate.model.Review;
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
 * ReviewDaoDB
 * The review data-access-object interface
 */
@Repository
@Profile("database")
public class ReviewDaoDB implements ReviewDao {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructs the ReviewDaoDB class
     * @param jdbcTemplate the JDBC Template
     */
    @Autowired
    public ReviewDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Adds a new review to the `Reviews` table in the database
     * @param review the new review
     * @return the new review updated with an auto-generated reviewID
     */
    @Override
    public Review add(Review review) {
        final String sql = "INSERT INTO `Review`(`LegislatureId`, `Username`, `UserState`, `UserComment`, `Rating`) VALUES(?, ?, ?, ?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, review.getLegislationID());
            statement.setString(2, review.getUsername());
            statement.setString(3, review.getState());
            statement.setString(4, review.getComments());
            statement.setInt(5, review.getRating());
            return statement;
        }, keyHolder);
        review.setReviewID(keyHolder.getKey().intValue());
        return review;
    }

    /**
     * Gets a list of all reviews from the `Reviews` table in the database
     * @return a list of all reviews
     */
    @Override
    public List<Review> getAllReviews() {
        final String sql = "SELECT `ReviewId`, `LegislatureId`, `Username`, `UserState`, `UserComment`, `Rating` FROM `Review`;";
        return jdbcTemplate.query(sql, new ReviewMapper());
    }

    /**
     * Gets a specified review from the `Reviews` table in the database
     * @param reviewID the ID of the specified review
     * @return the specified review
     */
    @Override
    public Review getReview(int reviewID) {
        try {
            final String sql = "SELECT `ReviewId`, `LegislatureId`, `Username`, `UserState`, `UserComment`, `Rating` FROM `Review` WHERE `ReviewId` = ?;";
            return jdbcTemplate.queryForObject(sql, new ReviewMapper(), reviewID);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * Gets a list of reviews from the `Reviews` table in the database
     * @param legislationID the ID of the specified legislation
     * @return the reviews
     */
    @Override
    public List<Review> getReviewsByLegislationID(int legislationID) {
        try {
            final String sql = "SELECT `ReviewId`, `LegislatureId`, `Username`, `UserState`, `UserComment`, `Rating` FROM `Review` WHERE `LegislatureId` = ?;";
            return jdbcTemplate.query(sql, new ReviewMapper(), legislationID);
        } catch (DataAccessException e) {
            return null;
        }
    }

    /**
     * Updates a specified user in the `Reviews` table in the database
     * @param review the specified review
     * @return true if the specified review exists and is updated
     */
    @Override
    public boolean update(Review review) {
        final String sql = "UPDATE `Review` SET `LegislatureId` = ?, `Username` = ?, `UserState` = ?, `UserComment` = ?, `Rating` = ? WHERE `ReviewId` = ?;";
        return jdbcTemplate.update(sql, review.getLegislationID(), review.getUsername(), review.getState(), review.getComments(), review.getRating(), review.getReviewID()) > 0;
    }

    /**
     * Deletes a specified review from the `Reviews` table in the database
     * @param reviewID the ID of the specified review
     * @return true if the specified review exists and is deleted
     */
    @Override
    public boolean delete(int reviewID) {
        final String sql = "DELETE FROM `Review` WHERE `ReviewId` = ?;";
        return jdbcTemplate.update(sql, reviewID) > 0;
    }

    /**
     * The review mapper class
     */
    private static final class ReviewMapper implements RowMapper<Review> {

        /**
         * Maps database rows to Review objects
         * @param rs the result set
         * @param index the index of the current row
         * @return the Review object
         * @throws SQLException an SQL exception
         */
        @Override
        public Review mapRow(ResultSet rs, int index) throws SQLException {
            Review review = new Review();
            review.setReviewID(rs.getInt("ReviewId"));
            review.setLegislationID(rs.getInt("LegislatureId"));
            review.setUsername(rs.getString("Username"));
            review.setState(rs.getString("UserState"));
            review.setComments(rs.getString("UserComment"));
            review.setRating(rs.getInt("Rating"));
            return review;
        }

    }

}
