package com.sg.data;

import com.sg.model.Review;

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
 * ReviewDao
 * The review data-access-object interface
 */
public interface ReviewDao {

    /**
     * Adds a new review to the `Reviews` table in the database
     * @param review the new review
     * @return the new review updated with an auto-generated reviewID
     */
    public Review add(Review review);

    /**
     * Gets a list of all reviews from the `Reviews` table in the database
     * @return a list of all reviews
     */
    public List<Review> getAllReviews();

    /**
     * Gets a specified review from the `Reviews` table in the database
     * @param reviewID the ID of the specified review
     * @return the specified review
     */
    public Review getReview(int reviewID);

    /**
     * Updates a specified user in the `Reviews` table in the database
     * @param review the specified review
     * @return true if the specified review exists and is updated
     */
    public boolean update(Review review);

    /**
     * Deletes a specified review from the `Reviews` table in the database
     * @param reviewID the ID of the specified review
     * @return true if the specified review exists and is deleted
     */
    public boolean delete(int reviewID);

}